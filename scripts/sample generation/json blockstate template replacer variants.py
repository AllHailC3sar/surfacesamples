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
stoneholder = "STONE"

namespath = askForPath("Names csv")
templatepath = askForPath("Template file")
parentspath = askForPath("Parents file")
outputpath = askForPath("Output folder",folder=True)


names = open(namespath,"r").read().split(",")
parents = open(parentspath,"r").read().split(",")
template = open(templatepath,"r").read()

for name in names:
    # for parent in parents:
    for j in range(ord(startingChar),(ord(startingChar)+totalVars)):
        variant = chr(j)
        open(outputpath+"/"+name+"_sample"+".json","w").write(template.replace(varholder, variant).replace(placeholder,name))
        outputFile = open(outputpath+"/"+name+"_sample"+".json","r").read()
        open(outputpath+"/"+name+"_sample"+".json","w").write(outputFile.replace(placeholder,name))

# parent+"_"+ &  goes in the correct place if doing samples
