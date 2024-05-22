# Run ELK Stack

## Clone code

```shell
git clone https://github.com/deviantony/docker-elk.git
cd docker-elk
```

## Configure Logstash

```shell
vim logstash/pipeline/logstash.conf
```

```text
tcp {
    ...
    codec => json_lines
}
```

## Docker compose up

```shell
docker compose up setup
docker compose up
```



# ELK Config

## Logstash

```text
input {
  tcp {
    port => 5055
    codec => json_lines
    ssl_enabled => true
    ssl_certificate => "/usr/share/logstash-ssl/logstash.crt"
    ssl_key => "/usr/share/logstash-ssl/logstash.key"
    ssl_client_authentication => required
    ssl_certificate_authorities => ["/usr/share/logstash-ssl/client.crt"]
    ssl_extra_chain_certs => ["/usr/share/logstash-ssl/ca.crt"]
  }
}
```


## Configure TCP SSL
### CA 인증서 생성

1. CA 개인 키 생성
```shell
openssl genpkey -algorithm RSA -out ca.key
```
2. CA 인증서 생성
```shell
openssl req -x509 -new -nodes -key ca.key -sha256 -days 3650 -out ca.crt
```

### 서버 인증서 생성
1. 서버 개인 키 생성
```shell
openssl genpkey -algorithm RSA -out logstash.key
```

2. 서버 인증서 서명 요청(CSR) 생성
```shell
openssl req -new -key logstash.key -out logstash.csr
```

3. CA에 의해 서버 인증서 서명
```shell
openssl x509 -req -in logstash.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out logstash.crt -days 3650 -sha256
```

### 클라이언트 인증서 생성
1. 클라이언트 개인 키 생성
```shell
openssl genpkey -algorithm RSA -out client.key
```

2. 클라이언트 인증서 서명 요청(CSR) 생성
```shell
openssl req -new -key client.key -out client.csr
```

3. CA에 의해 클라이언트 인증서 서명
```shell
openssl x509 -req -in client.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out client.crt -days 3650 -sha256
```
### 인증서 파일 변환 (PEM to PKCS12)
```shell
openssl pkcs12 -export -in client.crt -inkey client.key -out client.p12 -name client-cert -password pass:dornol1234
```

### TrustStore 생성
```shell
keytool -importcert -file ca.crt -alias logstashCA -keystore ca.jks -storepass dornol1234

# path 에 JAVA_HOME%bin 추가 필요 
```
