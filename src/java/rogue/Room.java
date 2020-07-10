package rogue;


class Room {
    
    int x1, x2, y1, y2;

    Point center;
    
    Room(int x, int y, int z, int width, int height){
        this.x1 = x;
        this.x2 = x + width;
        this.y1 = y;
        this.y2 = y + height;
        center = new Point((x1+x2) / 2, (y1+y2) / 2, z);
    }
    
    boolean intersects(Room room){
        return (x1 <= room.x2 && x2 >= room.x1 && y1 <= room.y2 && room.y2 >= room.y1);
    }

}
