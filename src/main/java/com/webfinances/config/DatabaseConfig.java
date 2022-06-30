package com.webfinances.config;

import com.webfinances.account.Account;
import com.webfinances.account.AccountRepo;
import com.webfinances.accounttype.AccountType;
import com.webfinances.accounttype.AccountTypeRepo;
import com.webfinances.category.Category;
import com.webfinances.category.CategoryRepo;
import com.webfinances.categorytype.CategoryType;
import com.webfinances.categorytype.CategoryTypeRepo;
import com.webfinances.subcategory.Subcategory;
import com.webfinances.subcategory.SubcategoryRepo;
import com.webfinances.transaction.Transaction;
import com.webfinances.transaction.TransactionRepo;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountRepo accountRepo, AccountTypeRepo accountTypeRepo,
                                        CategoryRepo categoryRepo, CategoryTypeRepo categoryTypeRepo,
                                        SubcategoryRepo subcategoryRepo, TransactionRepo transactionRepo) {
        return args -> {
            String imageUrl = "https://static.independent.co.uk/2021/12/07/10/PRI213893584.jpg?quality=75&width=990&auto=webp&crop=982:726,smart";

            // ACCOUNT TYPES
            AccountType cashAT = new AccountType("Cash", "https://www.pngarts.com/files/13/Cash-PNG-Image-Background.png");                           // id=imageUrl
            AccountType savingsAT = new AccountType("Savings Account", "https://www.clipartmax.com/png/full/285-2859190_savings-clip-art.png");   // id=imageUrl
            AccountType creditAT = new AccountType("Credit Card", "https://i.postimg.cc/vZXSZRMZ/credit-card.png");             // id=3
            AccountType debitAT = new AccountType("Debit Card", "https://www.pngkit.com/png/full/250-2504712_atm-card-png.png");               // id=4
            AccountType otherAT = new AccountType("Other", "https://i.postimg.cc/C1GpLPKM/pngwing-com-2.png");               // id=5
            accountTypeRepo.saveAll(List.of(cashAT, savingsAT, creditAT, debitAT, otherAT));


            // ACCOUNTS
//            Account cashA = new Account("cash", 58.16, cashAT); // id=imageUrl
//            Account creditA = new Account("creditt", 25.15, creditAT); // id=imageUrl
//            accountRepo.saveAll(List.of(cashA, creditA));


            // CATEGORY TYPES
            CategoryType incomeCT = new CategoryType("Income", 1); // id=imageUrl
            CategoryType expenseCT = new CategoryType("Expense", -1); // id=imageUrl
            categoryTypeRepo.saveAll(List.of(incomeCT, expenseCT));


            // CATEGORIES
            /////////////////////////////////////
            // Incomes
            Category wageC = new Category("Salary", "https://i.ibb.co/rMbKJxY/salary.png", incomeCT);                    // id=1
            Category rentalIncomeC = new Category("Rental Income", "https://favpng.com/editor/cache/511dd1b54d16fa71cfc95c17ac2570ca.png", incomeCT);   // id=2
            Category interestC = new Category("https://www.pngmart.com/files/7/Interest-Transparent-Images-PNG.png", imageUrl, incomeCT);   // id=3
            Category couponC = new Category("Coupon/Voucher", "https://flyclipart.com/downloadpage/images/voucher-coupon-png-icon-667681.png/667681", incomeCT);        // id=4
            Category incomeGiftC = new Category("Gift", "https://www.pngitem.com/pimgs/b/111-1112084_gift-png.png", incomeCT);              // id=5
            Category welfareC = new Category("Welfare", "https://www.clipartmax.com/png/full/203-2033809_st-louis-skyline-clipart.png", incomeCT);              // id=6
            Category refundC = new Category("https://www.pngitem.com/pimgs/b/390-3900045_refund-icon-png.png", imageUrl, incomeCT);                // id=7
            Category luckC = new Category("Lottery&Gambling", "https://www.kindpng.com/picc/b/376-3767328_dice-icon-png.png", incomeCT);        // id=8
            Category otherIncomeC = new Category("Other", "https://i.postimg.cc/C1GpLPKM/pngwing-com-2.png", incomeCT);            // id=9
            categoryRepo.saveAll(List.of(wageC, rentalIncomeC, interestC, couponC, incomeGiftC, welfareC,
                                        refundC, luckC, otherIncomeC));

            // Expenses
            Category foodAndDrinkC = new Category("Food & Drinks", imageUrl, expenseCT);   // id=9
            Category shoppingC = new Category("Shopping", imageUrl, expenseCT);            // id=10
            Category entertainmentC = new Category("Entertainment", imageUrl, expenseCT);  // id=11
            Category transportC = new Category("Transportation", imageUrl, expenseCT);     // id=12
            Category vehicleC = new Category("Vehicle", imageUrl, expenseCT);              // id=13
            Category taxesC = new Category("Taxes & Charges", imageUrl, expenseCT);        // id=14
            Category housingC = new Category("Housing", imageUrl, expenseCT);              // id=15
            Category investmentsC = new Category("Investments", imageUrl, expenseCT);      // id=16
            Category otherExpensesC = new Category("Other", imageUrl, expenseCT);          // id=17
            categoryRepo.saveAll(List.of(foodAndDrinkC, shoppingC, entertainmentC, transportC,
                    vehicleC, taxesC, housingC, investmentsC, otherExpensesC));


            // SUBCATEGORIES
            // Incomes
            Subcategory wageSC = new Subcategory("Salary", "https://i.ibb.co/rMbKJxY/salary.png", wageC);                            // id=1
            Subcategory rentalIncomeSC = new Subcategory("Rental Income", "https://favpng.com/editor/cache/511dd1b54d16fa71cfc95c17ac2570ca.png", rentalIncomeC);   // id=2
            Subcategory interestSC = new Subcategory("Interest/Dividend", "https://www.pngmart.com/files/7/Interest-Transparent-Images-PNG.png", interestC);       // id=3
            Subcategory couponSC = new Subcategory("Coupon/Voucher", "https://flyclipart.com/downloadpage/images/voucher-coupon-png-icon-667681.png/667681", couponC);              // id=4
            Subcategory incomeGiftSC = new Subcategory("Gift", "https://www.pngitem.com/pimgs/b/111-1112084_gift-png.png", incomeGiftC);                // id=5
            Subcategory welfareSC = new Subcategory("Welfare", "https://www.clipartmax.com/png/full/203-2033809_st-louis-skyline-clipart.png", welfareC);                   // id=6
            Subcategory refundSC = new Subcategory("Refund", "https://www.pngitem.com/pimgs/b/390-3900045_refund-icon-png.png", refundC);                      // id=7
            Subcategory luckSC = new Subcategory("Lottery&Gambling", "https://www.kindpng.com/picc/b/376-3767328_dice-icon-png.png", luckC);                // id=8
            Subcategory otherIncomeSC = new Subcategory("Other", "https://i.postimg.cc/C1GpLPKM/pngwing-com-2.png", otherIncomeC);             // id=9
            subcategoryRepo.saveAll(List.of(wageSC, rentalIncomeSC, interestSC, couponSC, incomeGiftSC, welfareSC,
                                    refundSC, luckSC, otherIncomeSC));


            // Expenses
            // Food & Drinks
            Subcategory groceriesSC = new Subcategory("Groceries", imageUrl, foodAndDrinkC);                  // id=1
            Subcategory restaurantSC = new Subcategory("Restaurant & Fast Food", imageUrl, foodAndDrinkC);    // id=2
            Subcategory barSC = new Subcategory("Bar & Cafe", imageUrl, foodAndDrinkC);                       // id=3
            Subcategory restrictedSC = new Subcategory("Alcohol & Cigarettes", imageUrl, foodAndDrinkC);      // id=4
            subcategoryRepo.saveAll(List.of(groceriesSC, restaurantSC, barSC, restrictedSC));


            // Shopping
            Subcategory clothesSC = new Subcategory("Clothes & Shoes", imageUrl, shoppingC);              // id=5
            Subcategory cosmeticsSC = new Subcategory("Cosmetics", imageUrl, shoppingC);                  // id=6
            Subcategory medicineSC = new Subcategory("Medicine", imageUrl, shoppingC);                    // id=7
            Subcategory electronicsSC = new Subcategory("Electronics", imageUrl, shoppingC);              // id=8
            Subcategory giftSC = new Subcategory("Gifts", imageUrl, shoppingC);                           // id=9
            Subcategory furnitureSC = new Subcategory("Furniture & Garden", imageUrl, shoppingC);         // id=10
            Subcategory accessoriesSC = new Subcategory("Accessories", imageUrl, shoppingC);              // id=11
            Subcategory kidsSC = new Subcategory("Kids", imageUrl, shoppingC);                            // id=12
            Subcategory petsSC = new Subcategory("Pets", imageUrl, shoppingC);                            // id=13
            subcategoryRepo.saveAll(List.of(clothesSC, cosmeticsSC, medicineSC, electronicsSC,
                                            giftSC, furnitureSC, accessoriesSC, kidsSC, petsSC));


            // Entertainment
            Subcategory eventsSC = new Subcategory("Culture & Sport Events", imageUrl, entertainmentC);        // id=14
            Subcategory travelSC = new Subcategory("Holidays & Trips", imageUrl, entertainmentC);              // id=15
            Subcategory sportSC = new Subcategory("Sport & Fitness", imageUrl, entertainmentC);                // id=16
            Subcategory hobbiesSC = new Subcategory("Hobbies", imageUrl, entertainmentC);                      // id=17
            Subcategory subscriptionsSC = new Subcategory("Subscriptions", imageUrl, entertainmentC);          // id=18
            Subcategory booksSC = new Subcategory("Books & Newsletters", imageUrl, entertainmentC);            // id=19
            Subcategory educationSC = new Subcategory("Education & Courses", imageUrl, entertainmentC);        // id=20
            Subcategory charitySC = new Subcategory("Charity", imageUrl, entertainmentC);                      // id=21
            Subcategory hazartSC = new Subcategory("Lottery & Gambling", imageUrl, entertainmentC);            // id=22
            subcategoryRepo.saveAll(List.of(eventsSC, travelSC, sportSC, hobbiesSC, subscriptionsSC, booksSC,
                                            educationSC, charitySC, hazartSC));

            // Transport
            Subcategory publicTransportSC = new Subcategory("Public Transport", imageUrl, transportC);     // id=23
            Subcategory taxiSC = new Subcategory("Taxi", imageUrl, transportC);                            // id=24
            Subcategory businessSC = new Subcategory("Business Trips", imageUrl, transportC);              // id=25
            Subcategory flightsSC = new Subcategory("Flights", imageUrl, transportC);                      // id=26
            Subcategory seaSC = new Subcategory("Sailing", imageUrl, transportC);                          // id=27
            Subcategory hamaliSC = new Subcategory("Furniture Moving", imageUrl, transportC);              // id=28
            Subcategory spodelenoSC = new Subcategory("Shared Travel", imageUrl, transportC);              // id=29
            subcategoryRepo.saveAll(List.of(publicTransportSC, taxiSC, businessSC, flightsSC, seaSC, hamaliSC, spodelenoSC));

            // Vehicle
            Subcategory fuelSC = new Subcategory("Fuel", imageUrl, vehicleC);                            // id=30
            Subcategory parkingSC = new Subcategory("Parking", imageUrl, vehicleC);                      // id=31
            Subcategory leasingSC = new Subcategory("Leasing", imageUrl, vehicleC);                      // id=32
            Subcategory carInsuranceSC = new Subcategory("Vehicle Insurance", imageUrl, vehicleC);          // id=33
            Subcategory remontSC = new Subcategory("Maintenance & Repairs", imageUrl, vehicleC);         // id=34
            Subcategory rentalSC = new Subcategory("Rentals", imageUrl, vehicleC);                       // id=35
            subcategoryRepo.saveAll(List.of(fuelSC, parkingSC, leasingSC, carInsuranceSC, remontSC, rentalSC));


            // Taxes & Charges
            Subcategory insuranceSC = new Subcategory("Insurances", imageUrl, taxesC);                  // id=36
            Subcategory finesSC = new Subcategory("Fines", imageUrl, taxesC);                           // id=37
            Subcategory loanSC = new Subcategory("Loans & Interests", imageUrl, taxesC);                // id=38
            Subcategory taxesSC = new Subcategory("Taxes", imageUrl, taxesC);                           // id=39
            Subcategory childSupportSC = new Subcategory("Child Support", imageUrl, taxesC);            // id=40
            Subcategory adviceSC = new Subcategory("Advisory", imageUrl, taxesC);                       // id=41
            subcategoryRepo.saveAll(List.of(insuranceSC, finesSC, loanSC, taxesSC, childSupportSC, adviceSC));


            // Housing
            Subcategory billsSC = new Subcategory("Bills & Utilities", imageUrl, housingC);              // id=42
            Subcategory repairsSC = new Subcategory("Repairs & Maintenance", imageUrl, housingC);        // id=43
            Subcategory rentSC = new Subcategory("Rent", imageUrl, housingC);                            // id=44
            Subcategory propertyInsuranceSC = new Subcategory("Property Insurance", imageUrl, housingC); // id=45
            Subcategory mortgageSC = new Subcategory("Mortgage", imageUrl, housingC);                    // id=46
            Subcategory servicesSC = new Subcategory("Services", imageUrl, housingC);                    // id=47
            subcategoryRepo.saveAll(List.of(billsSC, repairsSC, rentSC, propertyInsuranceSC, mortgageSC, servicesSC));


            // Investments
            Subcategory savingsSC = new Subcategory("Savings Deposit", imageUrl, investmentsC);                      // id=48
            Subcategory investmentsSC = new Subcategory("Stocks & Investments", imageUrl, investmentsC);    // id=49
            Subcategory realtySC = new Subcategory("Realty", imageUrl, investmentsC);                        // id=50
            //Subcategory collectionSC = new Subcategory("Cash Collection", imageUrl, R.drawable.ic_cash_collections);           // id=51
            subcategoryRepo.saveAll(List.of(savingsSC, investmentsSC, realtySC));

            // Other
            Subcategory otherExpenseSC = new Subcategory("Other", "https://i.postimg.cc/C1GpLPKM/pngwing-com-2.png", otherExpensesC);            // 52
            subcategoryRepo.saveAll(List.of(otherExpenseSC));


            // Accounts
            List<Account> accList = new ArrayList<>();
            for(int i = 0; i<31; i ++) {
                accList.add(new Account("acc"+i, i+100.3*i, cashAT, "BO5TBze8Ikcvn8pOJimMSpf0zh43"));
            }
            accountRepo.saveAll(accList);


            //  Transactions
            List<Transaction> tranList = new ArrayList<>();
            String date = "2022-07-0";
            Transaction tran;
            int j = 1;
            for(int i = 0; i<47; i++) {
                if(i % 2 == 0) {
                    tran = new Transaction(13.6*i, "note"+i, LocalDate.parse("2022-07-01"),
                            groceriesSC, accList.get(0), "BO5TBze8Ikcvn8pOJimMSpf0zh43");
                }
                else {
                    tran = new Transaction(13.6*i, "note"+i, LocalDate.parse("2022-07-01"),
                            groceriesSC, accList.get(1), "BO5TBze8Ikcvn8pOJimMSpf0zh43");
                }
                if(j < 10) {
                    tran.setDate(LocalDate.parse(date + String.valueOf(j)));
                }
                else {
                    j = 1;
                    tran.setDate(LocalDate.parse(date + String.valueOf(j)));
                }
                j++;
                tranList.add(tran);
            }
            transactionRepo.saveAll(tranList);
        };
    }
}
