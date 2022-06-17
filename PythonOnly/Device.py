from names_generator import generate_name
print("This is a basic device parity function, we'll have 4 devices and once you make a change on one it will change the others to match. ")
class Device:
    items = []
    
    def __init__(self, name, capacity):
        self.name = name
        self.capacity = capacity
        self.items = random_set()
        self.count = len(self.items)

    def random_set(self):
        items = {}
        for i in range(self.capacity):
            items.add(generate_name(style='capital'))
        return items

   
capacity = int(input("Enter in the initial capacity for all your devices "))
macbook = Device("Macbook", capacity)
iphone = Device("Iphone", capacity)
mac_mini = Device("Mac Mini", capacity)

items = {macbook, iphone, mac_mini}
    


print(macbook.name,"capacity is:", macbook.capacity)

