#include <HTTPClient.h>
#include <WiFi.h>
#include <WiFiClient.h>
#include <WebServer.h>
#include <mDNS.h>

#include <ArduinoJson.h>
#define calibration_factor 20000

#include <TM1637Display.h>

#define CLK 14
#define DIO 27

TM1637Display display(CLK, DIO);

int num0;
int num1;
int num2;
int num3;
int timen;
uint8_t data[] = { 0xff, 0xff, 0xff, 0xff };

#include <SPI.h>
#include <MFRC522.h>
// Module connection pins (Digital Pins)


#define RST_PIN 2  // Configurable, see typical pin layout above
#define SS_PIN 5   // Configurable, see typical pin layout above

MFRC522 mfrc522(SS_PIN, RST_PIN);  // Create MFRC522 instance

#define LEDG 26


const char* ssid = "abcdef";       // WIFI ID
const char* password = "87238723";  // WIFI PW

String origin = "http://211.107.188.212:8081/Senseohgym";
String cardId = "";

String result = "";  // 응답 결과 저장
HTTPClient http;     // 통신 객체
WiFiClient client;

void setup() {
  Serial.begin(115200);  // Baud rate

  display.setBrightness(8);  //밝기 설정 범위는 0 ~ 16

  uint8_t data[] = { 0xff, 0xff, 0xff, 0xff };
  display.setSegments(data);  //배열 출력
  delay(1000);

  data[0] = NULL;
  data[1] = NULL;
  data[2] = NULL;
  data[3] = NULL;
  display.setSegments(data);
  delay(1000);

  num0 = 0;
  num1 = 0;
  num2 = 0;
  num3 = 0;
  delay(1000);

  //============================================================================

  pinMode(LEDG, OUTPUT);

  //============================================================================

  // 통신 setup
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }

  Serial.println("Connected to the WiFi network");

  while (!Serial) {}                  // Do nothing if no serial port is opened (added for Arduinos based on ATMEGA32U4)
  SPI.begin();                        // Init SPI bus
  mfrc522.PCD_Init();                 // Init MFRC522
  delay(4);                           // Optional delay. Some board do need more time after init to be ready, see Readme
  mfrc522.PCD_DumpVersionToSerial();  // Show details of PCD - MFRC522 Card Reader details
  Serial.println(F("Scan PICC to see UID, SAK, type, and data blocks..."));
  // http.setTimeout(5000);
}

// 서버에 정보 보내기
bool sendGet(String path, String* output) {
  String url = origin + path;
  int retry = 5;
  bool result = false;
  while (retry-- && !result) {
    http.begin(client, url.c_str());
    Serial.print("[SEND GET] ");
    Serial.println(url);
    int httpCode = http.GET();  // 응답코드
    result = httpCode == 200;
    Serial.print("STATUS CODE: ");
    Serial.println(httpCode);
    if (result) {
      // delay(100); // 데이터가 다 들어오지 않으면 주석제거
      *output = http.getString();
    }

    http.end();
  }

  return result;
}

// 카드를 때면 정보 전달
bool toSend(String cardId, long totalMin){
  if(totalMin > 0){
    // 사용시간이 1분 미만이면 안보냄
    String url = "/GledCheck?cardId=" + cardId + "&machine=chestFly&done=1&totalMin=" + totalMin;
    sendGet(url, &result);
    Serial.print("Input result: ");
    Serial.println(result);
  }  
}

// 서버로부터 정보 요청
bool sendPost(String path, String buffer) {
  String url = origin + path;
  http.begin(client, url.c_str());
  Serial.print("[SEND POST] ");
  Serial.print(url);
  Serial.print(", ");
  Serial.println(buffer);
  int httpCode = http.POST(buffer);  // 응답코드
  http.end();                        // Free the resources

  return httpCode == 200;
}

unsigned long endTime = 0;
unsigned long startTime = 0;

// 타이머 시간 설정
void setTimer(int timer) {
  unsigned long now = millis();
  startTime = now;
  endTime = now + (timer * 60 * 1000);
}

// 타이머 작동
void updateTimer(unsigned long now) {
  unsigned long duration = endTime - now;
  bool dot = (duration / 500) % 2 == 1;
  duration /= 1000;
  unsigned long sec = duration % 60;
  unsigned long min = duration / 60;
  unsigned long time = min * 100 + sec;
  if (dot) {
    display.showNumberDecEx(time, 0b01000000);
  } else {
    display.showNumberDecEx(time);
  }
}


int flag;
bool cardExits() {
  int flag = 0;
  if (mfrc522.PICC_IsNewCardPresent()) {
    flag = 1;  // card present
  } else       //try again to catch the toggle effect
  {
    if (mfrc522.PICC_IsNewCardPresent())
      flag = 1;  // card present
  }

  return flag;
}

bool isFirst = true;
char str[32] = "";
void loop() {
  unsigned long now = millis();
  if (endTime > now) {
    digitalWrite(LEDG, HIGH);
    updateTimer(now);
  } else {
    digitalWrite(LEDG, LOW);
    display.showNumberDecEx(0);
  }

  if ((WiFi.status() == WL_CONNECTED)) {  //Check the current connection status
                                          // int Ccheck=0;
    // 카드 상태 확인
    if (!cardExits()) {
      // 카드를 때면
      if(!isFirst){
        long outTime = millis();
        long totalMin = (outTime - startTime) / (60 * 1000);
        toSend(cardId, totalMin);
        setTimer(0);
        Serial.println("card out");
      }
      isFirst = true;
     
      return;
    }

    if (!isFirst) {
      //카드가 붙어있는 동안
      Serial.println("card in");
      return;
    }

    isFirst = false;
    // Select one of the cards
    // 카드 태그 확인
    if (!mfrc522.PICC_ReadCardSerial()) {
      return;
    }

    Serial.println("ok1");
    // 카드 ID값을 String 값으로 변환
    array_to_string(mfrc522.uid.uidByte, 4, str);  //Insert (byte array, length, char array for output)
    Serial.println(str);                           //Print the output uid string

    cardId = str;
    String result = "";
    //server에 요청을 보내서 값 받아오는 코드
    String url = "/GledCheck?cardId=" + cardId + "&machine=chestFly&done=0&totalMin=0";
    sendGet(url, &result);
    Serial.print("Input result: ");
    Serial.println(result);

    DynamicJsonBuffer jsonBuffer;  // json 메모리 공간
    JsonObject& parsed = jsonBuffer.parseObject(result);

    int canuse = parsed["사용가능여부"];
    int usetime = parsed["사용시간"];

    Serial.println(canuse);
    Serial.println(usetime);
    // 예약을 했을때 설정 사용시간동안 사용 가능
    if (canuse == 1) {
      setTimer(usetime);
    // 예약이 없다면 정해진 사용시간(20분)동안 사용 가능
    } else if (canuse == 2) {
      setTimer(usetime);
    // 사용 불가능일 때는 초록LED가 2번 깜박임
    } else {
      digitalWrite(LEDG, HIGH);
      delay(300);
      digitalWrite(LEDG, LOW);
      delay(300);
      digitalWrite(LEDG, HIGH);
      delay(300);
      digitalWrite(LEDG, LOW);
      delay(300);
    }

    Serial.print("Reading: ");
  } else {
    Serial.println("Error on HTTP request");
  }

  delay(1000);
}

// 카드 ID 값을 String값으로 바꾸기 위한 작업 함수
void array_to_string(byte array[], unsigned int len, char buffer[]) {
  for (unsigned int i = 0; i < len; i++) {
    byte nib1 = (array[i] >> 4) & 0x0F;
    byte nib2 = (array[i] >> 0) & 0x0F;
    buffer[i * 2 + 0] = nib1 < 0xA ? '0' + nib1 : 'A' + nib1 - 0xA;
    buffer[i * 2 + 1] = nib2 < 0xA ? '0' + nib2 : 'A' + nib2 - 0xA;
  }
  buffer[len * 2] = '\0';
}