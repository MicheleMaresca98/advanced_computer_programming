import socket

IP = "localhost"
PORT = 5005
BACKLOG = 1
BUFFER_SIZE = 1024

print("[Server] listen at port " +str(PORT))

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((IP, PORT))
s.listen(BACKLOG)
conn, add = s.accept()

receivedData = conn.recv(BUFFER_SIZE)
print("[Server]: Connection address: {}".format(add)) 
print("[Server]: received data: " + receivedData.decode("utf-8"))
toClient = "The world never says hello back!\n"
conn.send(toClient.encode("utf-8"))

conn.close()
s.close()

