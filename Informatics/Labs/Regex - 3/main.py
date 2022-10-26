import random
import re


def generateEmojiByISU(isu):
    eyes = [":", ";", "X", "8", "="]
    nose = ["-", "<", "-{", "<{"]
    month = ["(", ")", "O", "|", "\\", "/", "P"]
    return eyes[isu % 5] + nose[isu % 4] + month[isu % 7]


def generateTestEmojiStr():
    list = ["hello", "world", "kotlin", "is", "cool", "and", "fun", "---", "I", "like", "it", "a", "lot"]
    count = random.randrange(start=2, stop=6)
    print("Emoji count expected = " + str(count))
    print("emoji = " + "\"" + generateEmojiByISU(isu) + "\"")
    for i in range(count):
        list.append(generateEmojiByISU(isu))
    random.shuffle(list)
    teststr = ' '.join(list)
    print("Autotest str: \"", teststr, "\"", sep="")
    countEmoji(teststr)


def countEmoji(emojistring):
    print("Emoji count = " + re
          .findall(generateEmojiByISU(isu), emojistring).__len__().__str__())


print("1 task\n")
print("ISU Number: ", end="")
isu = int(input())
generateTestEmojiStr()

print("\ndop task 1")
print("Variant №" + str(isu % 6) + " //isu number doesn't matter")


def generateVT():
    return random.choice(["ВТ - лучшая кафедра ИТМО", "ВТ - ван лав ИТМО", "ВТ плюс ИТМО = ♥"])


def generateText(log=False):
    list = ["Университет", "Привет", "Другие, ", "ВТ вообще не очень хорошая кафедра ИТМО, но в  в принципе ок",
            "Слова, ", "Текст", "Студент", "Осмысленность.", "вф", ":):):)", "Ректор", "qowei@niuitmo.tu"]
    count = random.randrange(start=1, stop=2)
    if log:
        print("Count expected = " + str(count))
    for i in range(count):
        list.append(generateVT())
    random.shuffle(list)
    teststr = ' '.join(list)
    if log:
        print("Autotest str: \"", teststr, "\"", sep="")
    return teststr


def countVT():
    print("Count = " + re.findall(r"ВТ(\s*?\S*?\s*?){1,4}ИТМО", generateText(True)).__len__().__str__())
    print((re.findall(r"ВТ(?:\s*?\S*?\s*?){1,4}ИТМО", generateText())[0]))


countVT()

print("\ndop task 2")
print("Variant №" + str(isu % 4) + " //isu number doesn't matter")


def generateExpr():
    return str(random.randint(0, 100)) + " + " + str(random.randint(0, 100)) + " = " + str(random.randint(0, 100))

def expr():
    exp = generateExpr()
    print("Autotest str: " + exp)
    find = re.findall(r"\d+", exp)
    result = str(3*int(find[0])**2 + 5) + " + " + str(3*int(find[1])**2 + 5) + " = " + str(3*int(find[2])**2 + 5)
    print(result)

expr()