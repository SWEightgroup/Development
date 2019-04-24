'''
get interface  
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

        # regular expression for count method with more than 4 parameters.
        fiveOrMoreParameter = re.findall(r"(private|protected|public)\w*\((\w*\,){4,}",data)
        # regular expression for count method with more than 4 parameters.
        eigthOrMoreParameter = re.findall(r"(private|protected|public)\w*\((\w*\,){7,}",data)
        # method count
        methodCountInline = re.findall(r"(private|protected|public)\w*\(\w*\)\{",data)
        methodCount = re.findall(r"(private|protected|public)\w*\(\w*\)\;",data)
        totMethodCount = len(methodCountInline) + len(methodCount)
        sumAllMethodCount += totMethodCount

        print(file)
        print(totMethodCount)

        # 
        #   FOR DEBUGGING
        
        if(len(fiveOrMoreParameter)>0):
            print(file)
            print("There are methods with five or more parameters")
        if(len(eigthOrMoreParameter)>0):
            print(file)
            print("There are methods with eigth or more parameters")

with open('results.txt', 'w') as result:
    result.write("analyzed files: {}\n".format(countFiles))

    result.write("analyzed method: {}\n".format(sumAllMethodCount))
    result.write("methods with five or more parameters: {}\n".format(len(fiveOrMoreParameter)))
    result.write("methods with eigth or more parameters: {}\n".format(len(eigthOrMoreParameter)))

