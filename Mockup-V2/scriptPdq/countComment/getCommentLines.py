'''
get comment line from files
'''

import os

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

for file in FILES:
    with open(file, 'r', encoding="utf-8") as ofile:
        multiline = False
        print(file)
        for num, line in enumerate(ofile, 1):
            COUNTLINE += 1
            line = line.strip()
            
            # fist check if multiline already open
            if multiline:
                COUNTCOMMENTS += 1
                # check for multiline closure
                if '*/' in line:
                    multiline = False

            if line and line[:2] == '/*' and not multiline:
                multiline = True
                COUNTCOMMENTS += 1
                # if multiline closes in the same row
                if '*/' in line:
                    multiline = False
            # check for single row comments
            if line and line[:2] == '//' and not multiline:
                COUNTCOMMENTS += 1

with open('results.txt', 'w') as result:
    result.write("total rows: {}\n".format(COUNTLINE))
    result.write("comment rows: {}\n".format(COUNTCOMMENTS))
    percentage = (COUNTCOMMENTS*100) / COUNTLINE
    result.write("percentage: {}\n".format(percentage))

