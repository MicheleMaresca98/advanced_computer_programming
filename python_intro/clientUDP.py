import socket

msgClient = "Ciao server!"
serverAddressPort = ("localhost", 7000)
bufferSize = 1024

s = socket.socket(family=socket.AF_INET,type= socket.SOCK_DGRAM)

print("[Client]: Invio dati al server")
s.sendto(msgClient.encode("utf-8"), serverAddressPort)

msgServer, addr = s.recvfrom(bufferSize)
print("[Client]: Risposta server: " + msgServer.decode("utf-8"))
s.close()
