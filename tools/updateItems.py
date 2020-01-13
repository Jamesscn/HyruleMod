itemsFile = open("items.csv", "r", encoding='utf-8-sig')
items = itemsFile.read().splitlines()
itemsFile.close()
langFile = open("../src/main/resources/assets/hyrule/lang/en_us.json", "w")
langFile.write("{\n")
for i in items:
    itemInfo = i.split(",")
    item = itemInfo[0]
    name = itemInfo[1]
    modelFile = open("../src/main/resources/assets/hyrule/models/item/" + item + ".json", "w")
    modelFile.write("{\n\t\"parent\": \"item/generated\",\n\t\"textures\": {\n\t\t\"layer0\": \"hyrule:item/" + item + "\"\n\t}\n}")
    modelFile.close()
    langFile.write("\t\"item.hyrule." + item + "\": \"" + name + "\",\n")
langFile.write("\t\"itemGroup.hyrule\": \"The Legend of Hyrule\"\n}")
langFile.close()