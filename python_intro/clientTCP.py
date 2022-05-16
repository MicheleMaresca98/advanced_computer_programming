import socket

IP = "localhost"
PORT = 5005

BUFFER_SIZE = 1024

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((IP.encode("utf-8"), PORT))
toServer = "HelloWorld"
s.send(toServer.encode("utf-8"))
receivedData = s.recv(BUFFER_SIZE)
print("[Client]: received: " + receivedData.decode("utf-8")) 
s.close()
