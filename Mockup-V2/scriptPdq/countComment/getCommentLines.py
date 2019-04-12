'''
get percentage of comment line from files
'''

import os

# all comments must be opened in a new line, otherwise they will not be counted
singleFileLine = 0
totCountLine = 0
singleFileComment = 0
totCountComment = 0

FILES = []
with open('paths.txt', 'r') as paths:
    for path in paths:
        path = path.strip('\n')
        for root, dirs, files in os.walk(path):
            for file in files:
                if (file.endswith(".js") or file.endswith(".java") or file.endswith(".jsx")):
                    FILES.append(os.path.join(root, file))

allResult = open('allResults.txt', 'w')
for file in FILES:
    with open(file, 'r', encoding="utf-8") as ofile:
        multiline = False
        singleFileLine = 0
        singleFileComment = 0
        for num, line in enumerate(ofile, 1):
            singleFileLine += 1
            line = line.strip()

            # fist check if multiline already open
            if multiline:
                singleFileComment += 1
                # check for multiline closure
                if '*/' in line:
                    multiline = False

            if line and line[:2] == '/*' and not multiline:
                multiline = True
                singleFileComment += 1
                # if multiline closes in the same row
                if '*/' in line:
                    multiline = False
            # check for single row comments
            if line and line[:2] == '//' and not multiline:
                singleFileComment += 1

        # line of comment of all the single files in "allResult.txt"
        allResult.write("{} line in file:  ".format(singleFileLine))
        allResult.write("{}\n".format(file))
        allResult.write("{} line of comment \n\n".format(singleFileComment))
        totCountLine += singleFileLine
        totCountComment += singleFileComment

allResult.close()

print("\n IT WORKS, file analyzed: {}, check results in \"result.txt\" \n".format(len(FILES)))

# metric result in "result.txt"
with open('results.txt', 'w') as result:
    result.write("COMMENT LINE PERCENTAGE \n\n")
    result.write("Total file analyzed: {}\n".format(len(FILES)))
    result.write("Total rows: {}\n".format(totCountLine))
    result.write("Comment rows: {}\n".format(totCountComment))
    percentage = (totCountComment*100) / totCountLine
    # only two decimal number
    percentage = round(percentage, 2)
    result.write("Percentage: {}\n".format(percentage))
