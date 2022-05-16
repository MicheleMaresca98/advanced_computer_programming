import socket

msgServer = "Ciao client!"
IP = "localhost"
PORT = 7000
BUFFER_SIZE = 1024
serverAddressPort = (IP.encode("utf-8"), PORT)

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(serverAddressPort)
msgClient, addr = s.recvfrom(BUFFER_SIZE)
print("[Server]: Messaggio ricevuto: " + msgClient.decode("utf-8"))
print("[Server]: Indirizzo client: {}".format(addr))

print("[Server]: Invio dati al client")
s.sendto(msgServer.encode("utf-8"), addr)

s.close()
