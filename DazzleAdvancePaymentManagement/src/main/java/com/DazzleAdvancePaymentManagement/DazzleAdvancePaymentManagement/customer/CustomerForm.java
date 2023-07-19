package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerForm {
    //customerId,customerName,customerJob,customerPaymentBalance
    @NotEmpty(message = "고객 UID는 필수항목입니다.")
    private Integer customerId;

    @NotEmpty(message = "성명은 필수항목입니다.")
    private String customerName;

    @NotEmpty(message = "소속(신분)은 필수항목입니다.")
    private String customerJob;

    @NotEmpty(message = "소유선수금은 필수항목입니다.")
    private Integer customerPaymentBalance;
}
