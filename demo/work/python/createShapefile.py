import shapefile

# Shapefileの読み込み
shp_path = 'demo\\work\\python\\P04-14_47_GML\\P04-14_47-g_MedicalInstitution.shp'
sf = shapefile.Reader(shp_path, encoding='cp932')

print("Geometry:")
for shape in sf.shapes():
    print(shape, '/', shape.shapeType, '/', shape.points)

# 属性データのフィールド名の表示
print("\nFields:")
fields = sf.fields[1:]  # 最初のフィールドは削除フラグであるためスキップ
field_names = [field[0] for field in fields]
print(field_names)

# 属性データの表示
print("\nRecords:")
for record in sf.records():
    print(record)

# py -m pip install pyshp