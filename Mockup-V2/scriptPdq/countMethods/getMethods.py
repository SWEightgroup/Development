'''
get methods for file (ONLY BACKEND FOLDER)
'''

import os
import re as re

# all comments must be opened in a new line, otherwise they will not be counted
FILES = []
with open('paths.txt','r') as paths:
    for path in paths:
        path = path.strip('\n')
        for root, dirs, files in os.walk(path):
            for file in files:
                if (file.endswith(".js") or file.endswith(".java") or file.endswith(".jsx")):
                    FILES.append(os.path.join(root, file))

sumAllMethodCount = 0
countFiles = 0
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
        data = data.replace("\\", "")
        data = data.replace("\"", "")
        data = data.replace("\'", "")
        data = data.replace(",", "")

        # regular expression for count filds in file
        # warning: if file have more than one class result could be wrong
        methodCountInline = re.findall(r"(private|protected|public)\w*\(\w*\)\{",data)
        methodCount = re.findall(r"(private|protected|public)\w*\(\w*\)\;",data)
        
        totMethodCount = len(methodCountInline) + len(methodCount)
        sumAllMethodCount += totMethodCount
        print(file)
        print(totMethodCount)
        
average = sumAllMethodCount / countFiles
print(average)

with open('results.txt', 'w') as result:
    result.write("analyzed files: {}\n".format(countFiles))
    result.write("methods average for file: {}\n".format(average))

