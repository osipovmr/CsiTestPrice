package service;

import entity.Price;
import java.util.ArrayList;
import static java.util.Objects.nonNull;

public class PriceService    {

    public static ArrayList<Price> updatePriceList(ArrayList<Price> oldPrices, ArrayList<Price> newPrices) {
        ArrayList<Price> updatedPrices = new ArrayList<>();
        if (
                (!nonNull(oldPrices))
                        &&
                        (!nonNull(newPrices))
        ) return updatedPrices;
        else if  (!nonNull(newPrices)) return oldPrices;
        else if  (!nonNull(oldPrices)) return newPrices;

        for (
                Price oldPrice : oldPrices) {
            for (Price newPrice : newPrices) {
                if (
                        (oldPrice.getProductCode().equals(newPrice.getProductCode()))
                        &&
                                (oldPrice.getNumber().equals(newPrice.getNumber()))
                        &&
                                (oldPrice.getDepart().equals(newPrice.getDepart()))
                ) {
                    if (DateComparator.isOldContainsNew(oldPrice.getBegin(), oldPrice.getEnd(), newPrice.getBegin(), newPrice.getEnd())) {
                        if (newPrice.getValue().equals(oldPrice.getValue())) {
                            updatedPrices.add(oldPrice);
                        } else {
                            try {
                                Price lastPrice = oldPrice.clone();
                                oldPrice.setEnd(newPrice.getBegin());
                                lastPrice.setBegin(newPrice.getEnd());
                                updatedPrices.add(oldPrice);
                                updatedPrices.add(lastPrice);
                                updatedPrices.add(newPrice);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }

                        }
                    } else if (DateComparator.isNewContainsOld(oldPrice.getBegin(), oldPrice.getEnd(), newPrice.getBegin(), newPrice.getEnd())) {
                        if (newPrice.getValue().equals(oldPrice.getValue())) {
                            updatedPrices.add(newPrice);
                        } else {
                            try {
                                Price lastPrice = newPrice.clone();
                                newPrice.setEnd(oldPrice.getBegin());
                                lastPrice.setBegin(oldPrice.getEnd());
                                updatedPrices.add(oldPrice);
                                updatedPrices.add(lastPrice);
                                updatedPrices.add(newPrice);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (DateComparator.isNewRight(oldPrice.getBegin(), oldPrice.getEnd(), newPrice.getBegin(), newPrice.getEnd())) {
                        if (newPrice.getValue().equals(oldPrice.getValue())) {
                            newPrice.setBegin(oldPrice.getBegin());
                            updatedPrices.add(newPrice);
                        } else {
                            oldPrice.setEnd(newPrice.getBegin());
                            updatedPrices.add(oldPrice);
                            updatedPrices.add(newPrice);
                        }
                    } else if (DateComparator.isNewLeft(oldPrice.getBegin(), oldPrice.getEnd(), newPrice.getBegin(), newPrice.getEnd())) {
                        if (newPrice.getValue().equals(oldPrice.getValue())) {
                            oldPrice.setBegin(newPrice.getBegin());
                            updatedPrices.add(oldPrice);
                        } else {
                            oldPrice.setBegin(newPrice.getEnd());
                            updatedPrices.add(oldPrice);
                            updatedPrices.add(newPrice);
                        }
                    }
                }
            }
        }
        return updatedPrices;
    }
}
