rm ../src/main/resources/assets/hyrule/models/item/*
rm ../src/main/resources/assets/hyrule/textures/item/*
cp textures/*.png ../src/main/resources/assets/hyrule/textures/item/
python updateItems.py
@echo Don't forget to modify your ModEventSubscriber file
pause