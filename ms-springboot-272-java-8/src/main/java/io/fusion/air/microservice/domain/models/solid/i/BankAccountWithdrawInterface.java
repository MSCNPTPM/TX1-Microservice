/**
 * (C) Copyright 2024 Araf Karsh Hamid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fusion.air.microservice.domain.models.solid.i;

import io.fusion.air.microservice.domain.models.solid.l.BankAccountBaseInterface;

/**
 * SOLID Principles
 *
 * Interface segregation principle (ISP)
 * No client should be forced to depend on methods it does not use.
 *
 * This means all the sub-classes of BankAccount Interface must implement the
 * following interfaces
 * 1. deposit()
 * 2. withdrawal()
 * 3. calculateInterest()
 *
 * The issue with above 3 methods available on Interface.
 * - All the implementing classes must have an implementation for all the 3 methods.
 *
 * Model with Issues
 *
 *                                              |-------------------------------|
 *                                             |   BankAccountAllService         |
 *                                            |     - deposit()                      |
 *                                           |     - withdrawal()                 |
 *                                          |     - calculateInterest()         |
 *                                         |--------------+----------------|
 *                                                          |
 *                         +------------------------+--------------------+
 *                         |                             |                          |
 *             |--------+-----------|    |-------+---------|   |--------+---------|
 *            |  Loan...Service      |    | Savings...Service |   | Checking...Service |
 *           |-------------------|    |------------------|   |-------------------|
 *
 *   In the above accounts the withdrawal happens only for Savings and Checking accounts
 *   and not for Loan Account.
 *   1. Savings Account
 *   2. Checking Account
 *   3. Loan Account
 *
 *   With the above model Loan account will be forced to implement a dummy withdraw() method.
 *
 *  Solution
 *  The interface is split into two and the implementing classes can use the appropriate methods.
 *  1. Loan Account Service implements only Bank Account Interface which contains only the
 *     following methods.
 *     - deposit()
 *     - calculateInterest()
 *   2. Savings and Checking Account Service implements two interfaces
 *      - BankAccount Interface
 *         - deposit()
 *         - calculateInterest()
 *      - BankAccountWithdrawInterface
 *         - withdraw()
 *
 *             |---------------------------|
 *            |  BankAccountInterface     |                |------------------------------------|
 *           |     - deposit()                |                 |  BankAccountWithdrawInterface    |
 *          |     - calculateInterest()    |                 |     - withdraw()                        |
 *         |------------+------+-------|                 |----------------+------------------|
 *                        |         |                                                |
 *                       |         +--------------------------+-----------+--------+
 *                      |                                           |                          |
 *           |--------+--------|                 |----------+------|    |----------+--------|
 *          |  Loan...Service   |                 | Savings...Service |    | Checking...Service |
 *         |-----------------|                 |-----------------|    |-------------------|
 *
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public interface BankAccountWithdrawInterface extends BankAccountBaseInterface {

    /**
     * Withdraw Amount
     * @param _withdrawalAmount
     */
    public void withdrawal(double _withdrawalAmount);

}
