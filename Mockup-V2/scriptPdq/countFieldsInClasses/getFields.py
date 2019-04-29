'''
get fields for file (ONLY BACKEND FOLDER)
'''

import os
import re

FILES = []
with open('paths.txt', 'r') as paths:
    for path in paths:
        path = path.strip('\n')
        for root, dirs, files in os.walk(path):
            for file in files:
                if (file.endswith(".js") or file.endswith(".java") or file.endswith(".jsx")):
                    FILES.append(os.path.join(root, file))


optimalRangeFile = 0
acceptableRangeFile = 0
notPassedFile = 0
countFiles = 0
totCountClasses = 0
classInSinglePage = 0

allResult = open('allResults.txt', 'w')
allResult.write("COUNT FIELDS FOR CLASS \n")

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

        classInSinglePage = 0
        # divide class text in the cells of array
        arrayOfClass = re.split(
            r'(privateclass|protectedclass|publicclass)', data)
        # print((re.split(
        #     r'(privateclass|protectedclass|publicclass)', data)))
        # > 1 because the first cell is out of class (if len(arrayOfClass) = 0 the file don't contain any classes)
        fieldsCount = []
        if(len(arrayOfClass) > 2):
            index = 2
            print("lenarray " + str(len(arrayOfClass)))

            # check fields in each class
            while(index < len(arrayOfClass)):
                totCountClasses += 1
                classInSinglePage += 1
                fieldsCount = re.findall(
                    r"(private|protected|public)\w*\=*\w*\;", arrayOfClass[index])
                index += 1
                print("incremento fields")
                allResult.write("file name: {}\n".format(file))
                allResult.write(
                    "classes in file: {}\n".format(classInSinglePage))
                allResult.write(
                    "fields in class: {}\n\n".format(len(fieldsCount)))

        #  ATTENZIONE: SE IN UN FILE C è UN INTERFACE è POSSIBILE CHE IL COUNT FIELD SIA MAGGIORE DI ZERO
        # MA IL COUNT DELLE CLASSI NON VIENE INCREMENTATO
        # vedi esempio in "../../Backend/src/main/java/it\colletta\repository\user\UserCustomQueryInterface.java"
        # ripensandoci in teoria l'array non viene splittato quindi se incremento il countfield dentro al while non dovrebbe incrementare
        print("\n")
        print(file)
        print("fields count " + str(len(fieldsCount)))
        print("class in single page " + str(classInSinglePage))
        print("number of classes " + str(totCountClasses))

        if(len(fieldsCount) <= 10):
            optimalRangeFile += 1
        if(10 < len(fieldsCount) < 15):
            acceptableRangeFile += 1
        if(len(fieldsCount) > 15):
            notPassedFile += 1


allResult.write("analyzed file: {}\n".format(countFiles))
allResult.write("number of classes: {}\n".format(totCountClasses))
allResult.close()


with open('results.txt', 'w') as result:
    # attenzione che in realtà conta i campi dati di ogni file, non sarebbe corretto ma in ogni file c è al massimo una classe
    result.write("COUNT FIELDS FOR CLASS \n")
    result.write("analyzed files: {}\n".format(countFiles))
    result.write("analyzed classes: {}\n".format(totCountClasses))
    result.write(
        "(<=10) classes with optimal range fields: {}\n".format(optimalRangeFile))
    result.write("(10<x<=15) classes with acceptable range fields: {}\n".format(
        acceptableRangeFile))
    result.write("(>15) not passed: {}\n".format(notPassedFile))
