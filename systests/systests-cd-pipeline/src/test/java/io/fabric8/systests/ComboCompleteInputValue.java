/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.systests;

import io.fabric8.utils.Millis;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Used to input values into a completion based combo list where we enter text; pause for the smart
 * completion list to pop up and then tab out of the field to forge the selection
 */
public class ComboCompleteInputValue extends InputValue {
    public ComboCompleteInputValue(WebDriverFacade facade, By by, String value) {
        super(facade, by, value);
    }

    @Override
    public String toString() {
        return "ComboCompleteInputValue{" +
                "by=" + getBy() +
                ", value='" + getValue() + '\'' +
                '}';
    }

    @Override
    protected void doInputOnElement(WebElement element) {
        super.doInputOnElement(element);

        // now lets wait a little bit
        getFacade().sleep(Millis.seconds(5));
        element.sendKeys(Keys.TAB);
        getFacade().sleep(Millis.seconds(2));
    }
}