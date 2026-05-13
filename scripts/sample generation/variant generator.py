import time
from tkinter.filedialog import askopenfilename,askdirectory
def askForPath(msg,folder=False,pause=1,end=": "):
    print(msg,end=end)
    time.sleep(pause)
    out=(askdirectory if folder else askopenfilename)()
    print(out)
    return out

startingChar="a"
totalVars=3

namespath = askForPath("Names csv")
names = open(namespath,"r").read().split(",")
data=[]
for name in names:data+=[(name+"_"+chr(i)) for i in range(ord(startingChar),(ord(startingChar)+totalVars))]
txt=",".join(data)
open(namespath[:-4]+"abc.csv","w").write(txt)