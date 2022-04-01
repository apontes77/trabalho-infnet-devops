# Create a new Web Droplet in the nyc1 region
resource "digitalocean_droplet" "maquina_labs_tf" {
  image    = "ubuntu-20-04-x64"
  name     = "maquina-labs-tf"
  region   = var.region
  size     = "s-1vcpu-2gb"
  ssh_keys = [data.digitalocean_ssh_key.my_key.id]
  tags = [
    "development"
  ]
}

resource "digitalocean_firewall" "fw_labs" {
  name = "fw-labs"

  droplet_ids = [digitalocean_droplet.maquina_labs_tf.id]

  inbound_rule {
    protocol         = "tcp"
    port_range       = "22"
    source_addresses = ["0.0.0.0/0", "::/0"]
  }

  outbound_rule {
    protocol              = "tcp"
    port_range            = "53"
    destination_addresses = ["0.0.0.0/0", "::/0"]
  }
}
