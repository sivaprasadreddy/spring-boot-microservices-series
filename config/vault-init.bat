rem !/bin/sh

VAULT_DEV_TOKEN=c7dcbe44-e621-1260-4e90-ffa1dc6838ca

vault login ${VAULT_DEV_TOKEN}

vault write secret/application @${CONFIG_DIR}/application.json
vault write secret/catalog-service @${CONFIG_DIR}/catalog-service.json

