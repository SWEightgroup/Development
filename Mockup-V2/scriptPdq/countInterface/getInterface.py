'''
get parameters 
'''

import os
import re as re

# all comments must be opened in a new line, otherwise they will not be counted
path =''
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

        methodCount = re.findall(r"interface",data)
        

        print(file)
        print(len(methodCount))

        # 
        #   FOR DEBUGGING

# with open('results.txt', 'w') as result:
#     result.write("analyzed files: {}\n".format(countFiles))

#     result.write("analyzed method: {}\n".format(sumAllMethodCount))
#     result.write("methods with five or more parameters: {}\n".format(len(fiveOrMoreParameter)))
#     result.write("methods with eigth or more parameters: {}\n".format(len(eigthOrMoreParameter)))

