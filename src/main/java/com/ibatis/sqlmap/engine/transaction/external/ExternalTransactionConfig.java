/**
 *    Copyright 2004-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.ibatis.sqlmap.engine.transaction.external;

import com.ibatis.sqlmap.engine.transaction.*;

import java.sql.SQLException;
import java.util.Properties;

public class ExternalTransactionConfig extends BaseTransactionConfig {

  private boolean defaultAutoCommit = false;
  private boolean setAutoCommitAllowed = true;

  public Transaction newTransaction(int transactionIsolation) throws SQLException, TransactionException {
    return new ExternalTransaction(dataSource, defaultAutoCommit, setAutoCommitAllowed, transactionIsolation);
  }

  public boolean isDefaultAutoCommit() {
    return defaultAutoCommit;
  }

  public void setDefaultAutoCommit(boolean defaultAutoCommit) {
    this.defaultAutoCommit = defaultAutoCommit;
  }

  public boolean isSetAutoCommitAllowed() {
    return setAutoCommitAllowed;
  }

  public void setSetAutoCommitAllowed(boolean setAutoCommitAllowed) {
    this.setAutoCommitAllowed = setAutoCommitAllowed;
  }

  public void setProperties(Properties props) throws SQLException, TransactionException {
    String dacProp = props.getProperty("DefaultAutoCommit");
    String sacaProp = props.getProperty("SetAutoCommitAllowed");
    defaultAutoCommit = "true".equals(dacProp);
    setAutoCommitAllowed = "true".equals(sacaProp) || sacaProp == null;
  }

}
