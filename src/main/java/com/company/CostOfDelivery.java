package com.company;

import lombok.Getter;

public class CostOfDelivery {

    @Getter
    private double cost;

    public double calculate(int distance, int length, int width, int height, boolean fragility, Workload workload) {
        calculateCostDependingOnDistance(distance);
        calculateCostDependingOnDimensionsOfCargo(length, width, height);
        calculateCostDependingOnFragility(fragility, distance);
        calculateCostDependingOnWorkloadFactor(workload);
        calculateMinCost();
        return cost;
    }

    private void calculateCostDependingOnDistance(int distance) {
        if (distance <= 2) {
            cost += 50;
        } else if (distance > 2 && distance <= 10) {
            cost += 100;
        } else if (distance > 10 && distance <= 30) {
            cost += 200;
        } else if (distance > 30) {
            cost += 300;
        }
    }

    private void calculateCostDependingOnDimensionsOfCargo(int length, int width, int height) {
        int dimension = length + width + height;

        if (dimension < 100) {
            cost += 100;
        } else  {
            cost += 200;
        }
    }

    private void calculateCostDependingOnFragility(boolean fragility, int distance) {
        if (fragility) {
            if(checkDistanceLessThan30Km(distance)){
                cost += 300;
            } else cost = 0;
        }
    }

    private void calculateCostDependingOnWorkloadFactor(Workload workload) {
        switch (workload){
            case VERY_HIGH:{
                cost *= 1.6;
                break;
            }
            case HIGH:{
                cost *= 1.4;
                break;
            }
            case INCREASED:{
                cost *= 1.2;
                break;
            }
            default:
                cost *= 1;
        }
    }

    private void calculateMinCost(){
        if (cost < 400 && cost != 0){
            cost = 400;
        }
    }

    private static boolean checkDistanceLessThan30Km(double distance) {
        if(distance > 30){
            System.out.println("Хрупкие грузы нельзя возить на расстояние более 30 км");
            return false;
        } else return true;
    }
}
