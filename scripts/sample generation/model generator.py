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

orespath = askForPath("Ores csv")
templatepath = askForPath("Template file")
parentspath = askForPath("Parents file")
outputpath = askForPath("Output folder",folder=True)


ores = open(orespath,"r").read().split(",")
parents = open(parentspath,"r").read().split(",")
template = open(templatepath,"r").read()

for ore in ores:
    name = input("File name for "+ore+": ")
    for parent in parents:
        for j in range(ord(startingChar),(ord(startingChar)+totalVars)):
            variant = chr(j)
            open(outputpath+"/"+parent+"_"+name+"_sample_"+variant+".json","w").write(template.replace(varholder, variant).replace(placeholder,ore))
            outputFile = open(outputpath+"/"+parent+"_"+name+"_sample_"+variant+".json","r").read()
            open(outputpath+"/"+parent+"_"+name+"_sample_"+variant+".json","w").write(outputFile.replace(placeholder,ore).replace(stoneholder,parent))
