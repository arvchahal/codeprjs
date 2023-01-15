import math

side1 = float(input("Input the side of an equilateral or right triangle: "))
hypotenuse = float(input("Input the hypotenuse of a right triangle or set 0 for an equilateral triangle: "))


class Triangle():

    def getArea(self):
        pass

    def getPerimeter(self):
        pass


class equilateralTriangle(Triangle):
    def getArea(self):
        if hypotenuse == 0:
            y = math.sqrt(3) / 4 * (side1) * (side1)
            return print("The area of the equilateral triangle is: %.1f" % y)

    def getPerimeter(self):
        return print("The perimeter of the equilateral triangle is:", side1 * 3)


class Right_Triangle(Triangle):
    def getArea(self):
        side2 = math.sqrt(math.pow(hypotenuse, 2) - math.pow(side1, 2))
        return print("The area of the right triangle is: %.1f" % (.5 * side1 * side2))

    def getPerimeter(self):
        side2 = math.sqrt(math.pow(hypotenuse, 2) - math.pow(side1, 2))
        return print("The perimeter of the right traingle is: %.1f" %(side2 + side1 + hypotenuse))

    def getRatio(self):
        return print("The ratio of the sides of the first side to the hypotenuse is: %.1f" % (side1 / hypotenuse))


if hypotenuse == 0:
    tr = equilateralTriangle()
    tr.getArea()
    tr.getPerimeter()
else:
    tr = Right_Triangle()
    tr.getArea()
    tr.getPerimeter()
    tr.getRatio()
