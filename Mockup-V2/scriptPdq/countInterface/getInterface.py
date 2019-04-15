'''
get numbers of interfaces for each packages
'''

import os
import re

# all comments must be opened in a new line, otherwise they will not be counted
path = ''
FILES = []
with open('paths.txt', 'r') as paths:
    for path in paths:
        path = path.strip('\n')
        for root, dirs, files in os.walk(path):
            for file in files:
                if (file.endswith(".js") or file.endswith(".java") or file.endswith(".jsx")):
                    FILES.append(os.path.join(root, file))

countFiles = 0
countInterface = 0
packageName = ''
packageList = []
packageDictionarie = {}
for file in FILES:
    countFiles += 1
    with open(file, 'r', encoding="utf-8") as ofile:
        data = ofile.read()

        # trim
        data = data.replace(".", "")
        data = data.replace("<", "")
        data = data.replace(">", "")
        data = data.replace("[", "")
        data = data.replace("]", "")
        data = data.replace(" ", "")
        data = data.replace("\n", "")

        packageList = re.split(r"package(\w+)\;", data)
        countInterface = re.findall(
            r"(public|private|protected)interface", data)
        print(len(countInterface))

        # maybe this check is worthless
        if(len(packageList) > 0):
            packageName = packageList[1]

        if((packageName not in packageDictionarie)):
            packageDictionarie[packageName] = len(countInterface)
        else:
            packageDictionarie[packageName] += len(countInterface)


for string, occurrence in packageDictionarie.items():
    print("il package " + string + " contiene " +
          str(occurrence) + " interfaces")

with open('results.txt', 'w') as result:
    result.write("analyzed files: {}\n".format(countFiles))

    for string, occurrence in packageDictionarie.items():
        result.write("there are {} interfaces ".format(str(occurrence)))
        result.write("in package: {}\n".format(string))

#  MANCA DA FARE LA PERCENTUALE
