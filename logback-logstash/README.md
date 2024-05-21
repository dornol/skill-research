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