package com.moslite.orderbackend.services.validation;

import com.moslite.orderbackend.domain.Cliente;
import com.moslite.orderbackend.domain.enums.TipoCliente;
import com.moslite.orderbackend.dto.ClienteNewDTO;
import com.moslite.orderbackend.resources.exceptions.FieldMessage;
import com.moslite.orderbackend.services.ClienteService;
import com.moslite.orderbackend.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    ClienteService clienteService;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
        }

        Cliente cliente = clienteService.findByEmail(objDto.getEmail());
        if (cliente != null) {
            list.add(new FieldMessage("email", "E-mail já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}