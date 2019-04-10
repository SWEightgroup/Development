'''
get fields for file (ONLY BACKEND FOLDER)
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


OptimalRangeFile = 0
acceptableRangeFile = 0
notPassedFile = 0
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

        # regular expression for count filds in file
        # warning: if file have more than one class result could be wrong
        fieldsCount = re.findall(r"(private|protected|public)\w*\=*\w*\;",data)
        print(file)
        print(len(fieldsCount))
        
        if(len(fieldsCount) <= 10):
            OptimalRangeFile += 1
        if(10 < len(fieldsCount) < 15):
            acceptableRangeFile += 1
        if(len(fieldsCount) > 15):
            notPassedFile += 1
        
        #   FOR DEBUGGING
        if(len(fieldsCount)>9):
            print("file:" + file)
            print("have " + str(len(fieldsCount)) + " fields")

with open('results.txt', 'w') as result:
    result.write("analyzed files: {}\n".format(countFiles))
    result.write("files with optimal range: {}\n".format(OptimalRangeFile))
    result.write("files with acceptable range: {}\n".format(acceptableRangeFile))
    result.write("not passed: {}\n".format(notPassedFile))

