#!/bin/sh

VAULT_DEV_TOKEN=934f9eae-31ff-a8ef-e1ca-4bea9e07aa09

vault auth ${VAULT_DEV_TOKEN}

vault write secret/application @${CONFIG_DIR}/application.json
vault write secret/catalog-service @${CONFIG_DIR}/catalog-service.json

