import time
from tkinter.filedialog import askopenfilename,askdirectory
def askForPath(msg,folder=False,pause=1,end=": "):
    print(msg,end=end)
    time.sleep(pause)
    out=(askdirectory if folder else askopenfilename)()
    print(out)
    return out

placeholder="TEMPLATE"

namespath = askForPath("Names csv")
templatepath = askForPath("Template file")
outputpath = askForPath("Output folder",folder=True)
names = open(namespath,"r").read().split(",")
template = open(templatepath,"r").read()
for name in names:open(outputpath+"/"+name+"_sample.json","w").write(template.replace(placeholder,name))