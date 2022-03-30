resource "azurerm_resource_group" "postgresql-rg" {
  name = "alexandrequeiroz-postgresql-rg"
  location = "North Europe"
}

resource "azurerm_postgresql_server" "postgresql-server" {
  location            = azurerm_resource_group.postgresql-rg.location
  name                = "alexandrequeiroz-postgresql-rg"
  resource_group_name = azurerm_resource_group.postgresql-rg.name
  sku_name            = var.postgresql-sku-name
  version             = var.postgresql-version

  administrator_login = var.postgresql-admin-login
  administrator_login_password = var.postgresql-admin-password

  storage_mb = var.postgresql-storage
  auto_grow_enabled = true

  backup_retention_days = 7
  geo_redundant_backup_enabled = false

  public_network_access_enabled = true
  ssl_enforcement_enabled = true
  ssl_minimal_tls_version_enforced = "TLS1_2"
}

resource "azurerm_postgresql_database" "postgresql-db" {
  name = "alexandrequeirozdb"
  resource_group_name = azurerm_resource_group.postgresql-rg.name
  server_name = azurerm_postgresql_server.postgresql-server.name
  charset = "utf8"
  collation = "English_United States.1252"
}

resource "azurerm_postgresql_firewall_rule" "postgresql-fw-rule" {
  end_ip_address      = "192.168.1.12"
  name                = "PostgreSQL Office Access"
  resource_group_name = azurerm_resource_group.postgresql-rg.name
  server_name         = azurerm_postgresql_server.postgresql-server.name
  start_ip_address    = "192.168.1.12"
}