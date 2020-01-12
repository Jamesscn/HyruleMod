rm ../src/main/resources/assets/hyrule/models/item/*
rm ../src/main/resources/assets/hyrule/textures/item/*
cp textures/*.png ../src/main/resources/assets/hyrule/textures/item/
python updateItems.py