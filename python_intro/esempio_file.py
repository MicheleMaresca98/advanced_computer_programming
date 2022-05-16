f = open("prova.txt",'w')
f.write("Sovrascrivo il contenuto\n")
f.close()

f = open("prova.txt",'a')
f.write("Non sovrascivo il contenuto\n")
f.close()

f = open("prova.txt",'r')
print(f.read())
f.close()
