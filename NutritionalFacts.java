package ms_code.Math381;

/**
 * Created by saketp on 1/30/2016.
 */
public class NutritionalFacts {
    private String itemName;
    private int servingSize, id;
    private double calories, sodium, totalCarbs, fiber, protein, calcium, iron, vitaminA, vitaminC, satFat, totalFat, phosp, water;
    private double sugar, magnesium, potassium, zinc, copper, manganese, selenium, thiamin, riboflavin, niacin, panto_acid, vitB6;


    public NutritionalFacts(String itemName, int id, double calories, double sodium, double totalCarbs, double fiber,
                            double protein, double calcium, double iron, double vitaminC, double totalFat, double phosp,
                            double water, double sugar, double magnesium, double potassium, double zinc, double copper,
                            double manganese, double selenium,double thiamin, double riboflavin, double niacin,
                            double panto_acid, double vitB6) {
        double factor = 100.0 / (100.0 - water);
        if (factor > 20) {
            factor = 20;
        }

        this.servingSize = 100;
        this.itemName = itemName;
        this.id = id;
        this.calories = calories * factor;
        this.sodium = sodium * factor;
        this.totalCarbs = totalCarbs * factor;
        this.fiber = fiber * factor;
        this.protein = protein * factor;
        this.calcium = calcium * factor;
        this.iron = iron * factor;
        this.vitaminC = vitaminC * factor;
        this.satFat = satFat * factor;
        this.totalFat = totalFat * factor;
        this.phosp = phosp * factor;
        this.water = water * factor;
        this.sugar = sugar * factor;
        this.magnesium = magnesium * factor;
        this.potassium = potassium * factor;
        this.zinc = zinc * factor;
        this.copper = copper * factor;
        this.manganese = manganese * factor;
        this.selenium = selenium * factor;
        this.thiamin = thiamin * factor;
        this.riboflavin = riboflavin * factor;
        this.niacin = niacin * factor;
        this.panto_acid = panto_acid * factor;
        this.vitB6 = vitB6 * factor;
    }


    public String toString() {
        return "Item Name: " + itemName + " serving size: " + servingSize + " calories: " + calories + " total fat: " +
                totalFat + " sodium: " + sodium + " total carbs: " + totalCarbs + " fiber: " + fiber + " protein: " + protein + "\n"
                + " Calcium: " + calcium + " Iron: " + iron + " Vitamin A: " + vitaminA + " Vitamin C: " + vitaminC + " Saturated Fat: " + satFat;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
    }

    public double getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(double vitaminA) {
        this.vitaminA = vitaminA;
    }

    public double getSatFat() {
        return satFat;
    }

    public void setSatFat(double satFat) {
        this.satFat = satFat;
    }

    //returns an array representation of the contents of the object
    public double[] returnArray() {
        double[] list = new double[22];
        list[0] = calories;
        list[1] = sodium;
        list[2] = totalCarbs;
        list[3] = fiber;
        list[4] = protein;
        list[5] = calcium;
        list[6] = iron;
        list[7] = vitaminC;
        list[8] = totalFat;
        list[9] = phosp;
        list[10] = sugar;
        list[11] = magnesium;
        list[12] = potassium;
        list[13] = zinc;
        list[14] = copper;
        list[15] = manganese;
        list[16] = selenium;
        list[17] = thiamin;
        list[18] = riboflavin;
        list[19] = niacin;
        list[20] = panto_acid;
        list[21] = vitB6;
        return list;

    }
}
