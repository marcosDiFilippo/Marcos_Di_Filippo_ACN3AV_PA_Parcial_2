package model;

import java.sql.Timestamp;

public class Transaction {
    private long id;
    private long userId;
    private long bankTransactionTypeId;
    private String typeName;
    private double amount;
    private String description;
    private Timestamp createdAt;

    public long getId() { 
        return id; 
    }
    public void setId(long id) { 
        this.id = id; 
    }

    public long getUserId() { 
        return userId; 
    }
    public void setUserId(long userId) { 
        this.userId = userId; 
    }

    public long getBankTransactionTypeId() { 
        return bankTransactionTypeId; 
    }
    public void setBankTransactionTypeId(long bankTransactionTypeId) { 
        this.bankTransactionTypeId = bankTransactionTypeId; 
    }

    public String getTypeName() { 
        return typeName; 
    }
    public void setTypeName(String typeName) { 
        this.typeName = typeName; 
    }

    public double getAmount() { 
        return amount; 
    }
    public void setAmount(double amount) { 
        this.amount = amount; 
    }

    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }

    public Timestamp getCreatedAt() { 
        return createdAt; 
    }
    public void setCreatedAt(Timestamp createdAt) { 
        this.createdAt = createdAt; 
    }
}
