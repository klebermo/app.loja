package org.loja.payment.pagseguro;

import java.math.BigDecimal;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AcceptedPaymentMethodsBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

public class Checkout {

    public Boolean register(){
        org.loja.settings.pagseguro.PagSeguroDao pagSeguroDao = new org.loja.settings.pagseguro.PagSeguroDao();
        org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(pagSeguroDao);
        org.loja.settings.pagseguro.PagSeguro pagSeg = (org.loja.settings.pagseguro.PagSeguro) pagSeguroDao.get();
        String appId = pagSeg.getClientId();
        String appKey = pagSeg.getClientSecret();

        try {
            final PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(appId, appKey), PagSeguroEnv.SANDBOX);
            RegisteredCheckout registeredCheckout = pagSeguro.checkouts().register(
                new CheckoutRegistrationBuilder()
                    .withCurrency(Currency.BRL)
                    .withSender(new SenderBuilder().withEmail("kleber@mail.com").withName("Kleber").withCPF("xxxxxxxxxxx").withPhone(new PhoneBuilder().withAreaCode("73").withNumber("988039934")))
                    .addItem(new PaymentItemBuilder().withId("0001").withDescription("Produto PagSeguroI").withAmount(new BigDecimal(9.99)).withQuantity(1).withWeight(1000))
                    .withAcceptedPaymentMethods(new AcceptedPaymentMethodsBuilder()
                        .addInclude(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.BALANCE))
                        .addInclude(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.BANK_SLIP))
                        .addInclude(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.CREDIT_CARD))
                    )
                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.BALANCE))
                        .withConfig(new ConfigBuilder().withKey(ConfigKey.DISCOUNT_PERCENT).withValue(new BigDecimal(10.00)))
                    )
                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder().withGroup(PaymentMethodGroup.BANK_SLIP))
                        .withConfig(new ConfigBuilder().withKey(ConfigKey.DISCOUNT_PERCENT).withValue(new BigDecimal(10.00)))
                    )
            );
            System.out.println(registeredCheckout.getRedirectURL());
            return true;
        }catch (Exception e){
          e.printStackTrace();
          return false;
        }
    }
}
