import time
from tkinter.filedialog import askopenfilename,askdirectory
def askForPath(msg,folder=False,pause=1,end=": "):
    print(msg,end=end)
    time.sleep(pause)
    out=(askdirectory if folder else askopenfilename)()
    print(out)
    return out

startingChar="a"
totalVars=4

placeholder="TEMPLATE"
varholder = "VAR"

namespath = askForPath("Names csv")
templatepath = askForPath("Template file")
outputpath = askForPath("Output folder",folder=True)
names = open(namespath,"r").read().split(",")
template = open(templatepath,"r").read()
for name in names:
    for i in range(ord(startingChar),(ord(startingChar)+totalVars)):
        variant = chr(i)
        open(outputpath+"/"+name+"_sample_"+variant+".json","w").write(template.replace(varholder, variant))
        outputFile = open(outputpath+"/"+name+"_sample_"+variant+".json","r").read()
        open(outputpath+"/"+name+"_sample_"+variant+".json","w").write(outputFile.replace(placeholder,name))
