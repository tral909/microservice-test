#!/bin/bash

# Локальный запуск рэбита для ручных тестов
docker run --rm -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=pass bitnami/rabbitmq:latest