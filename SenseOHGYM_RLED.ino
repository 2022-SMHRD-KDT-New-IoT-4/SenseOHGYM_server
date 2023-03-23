#include <HTTPClient.h>
#include <WiFi.h>
#include <WiFiClient.h>
#include <WebServer.h>
#include <mDNS.h>

#include <ArduinoJson.h>
#define calibration_factor 20000

#define LEDR 25

const char* ssid = "abcdef";        // WIFI ID
const char* password = "87238723";  // WIFI PW

String origin = "http://211.107.188.212:8081/Senseohgym";

String result = "";  // 응답 결과 저장
HTTPClient http;     // 통신 객체
WiFiClient client;

void setup() {
  Serial.begin(115200);  // Baud rate

  //============================================================================

  pinMode(LEDR, OUTPUT);

  //============================================================================

  // 통신 setup
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }

  Serial.println("Connected to the WiFi network");
}

bool sendGet(String path, String* output) {
  String url = origin + path;
  http.begin(client, url.c_str());
  Serial.print("[SEND GET] ");
  Serial.println(url);
  int httpCode = http.GET();  // 응답코드
  bool result = httpCode == 200;
  Serial.println(httpCode);
  if(result){
    // delay(100); // 데이터가 다 들어오지 않으면 주석제거
    *output = http.getString();  
  }

  http.end();                                             
  return result;
}


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

void loop() {
  if ((WiFi.status() == WL_CONNECTED)) {  //Check the current connection status
  
    String result = "";
    //server에 요청을 보내서 값 받아오는 코드
    String url = "/RledCheck?machine=chestFly";
    sendGet(url,&result);
    Serial.print("Input times: ");
    Serial.println(result);

    DynamicJsonBuffer jsonBuffer; // json 메모리 공간
    JsonObject& parsed = jsonBuffer.parseObject(result);

    int userR = parsed["예약유무"];

    Serial.println(userR);

    if(userR == 1){
      // 사용 기기에 예약이 있을시 빨간LED ON
      digitalWrite(LEDR, HIGH);
    }else{
      // 사용 기기에 예약이 없으면 빨간LED OFF
      digitalWrite(LEDR, LOW);
    }

    Serial.print("Reading: ");
    
    delay(2000);
  } else {
    Serial.println("Error on HTTP request");
  }
  delay(1000);
}
