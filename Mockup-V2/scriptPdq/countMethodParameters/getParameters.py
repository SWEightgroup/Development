'''
get comment line from files
'''

import os
import re as re

# all comments must be opened in a new line, otherwise they will not be counted
COUNTLINE = 0
COUNTCOMMENTS = 0
FILES = []
with open('paths.txt','r') as paths:
    for path in paths:
        path = path.strip('\n')
        for root, dirs, files in os.walk(path):
            for file in files:
                if (file.endswith(".js") or file.endswith(".java") or file.endswith(".jsx")):
                    FILES.append(os.path.join(root, file))

countFiles = 0
for file in FILES:
    countFiles += 1
    with open(file, 'r', encoding="utf-8") as ofile:
        data = ofile.read()
        # regular expression for count method with more than 3 parameters.
        
        data = data.replace(".", "")
        data = data.replace("<", "")
        data = data.replace(">", "")
        data = data.replace("[", "")
        data = data.replace("]", "")
        data = data.replace(" ", "")
        data = data.replace("\n", "")

        fiveOrMoreParameter = re.findall(r"(private|protected|public)\w*\((\w*\,){4,}",data)
        eigthOrMoreParameter = re.findall(r"(private|protected|public)\w*\((\w*\,){4,}",data)
        if(len(fiveOrMoreParameter)>0):
            print(file)
            print("There are methods with five or more parameters")
        if(len(eigthOrMoreParameter)>0):
            print(file)
            print("There are methods with eigth or more parameters")

with open('results.txt', 'w') as result:
    result.write("analyzed files: {}\n".format(countFiles))
    result.write("methods with five or more parameters: {}\n".format(len(fiveOrMoreParameter)))
    result.write("methods with eigth or more parameters: {}\n".format(len(eigthOrMoreParameter)))

