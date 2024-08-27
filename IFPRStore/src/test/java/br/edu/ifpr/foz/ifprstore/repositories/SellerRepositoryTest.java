package br.edu.ifpr.foz.ifprstore.repositories;
import br.edu.ifpr.foz.ifprstore.models.Seller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class SellerRepositoryTest {
    @Test
    public void ExibirUmaListaDeSellers(){
        SellerRepository repository = new SellerRepository();

        List<Seller> seller = repository.getSellers();
        for(Seller s: seller){
            System.out.println(s);
        }
    }

    @Test
    public void InserirUmRegistroNaTabelaSeller(){
        SellerRepository repository = new SellerRepository();
        Seller sellerFake= new Seller();
        sellerFake.setName("Frodo");
        sellerFake.setEmail("frodo@gmail.com");
        sellerFake.setBirthDate(LocalDate.of(2024,8,5));
        sellerFake.setBaseSalary(100000.0);


        repository.insert(sellerFake);
    }

    @Test
    public void AtualizarOSalarioDeUmSellerDeUmDepartamento(){
        SellerRepository repository = new SellerRepository();
        repository.updateSalary(1,1500.00);
    }

    @Test
    public void DeletarUmSeller(){
        SellerRepository repository = new SellerRepository();
        repository.delete(8);
    }

    @Test
    public void RetornarUmSellerPeloId(){
        SellerRepository repository = new SellerRepository();
        Seller seller = repository.getById(1);
        System.out.println(seller);
        System.out.println("-----DEPARTAMENTO-----");
        System.out.println(seller.getDepartment());
    }

    @Test
    public void RetornarUmaListaDeSellersPeloIdDoDepartament(){
        SellerRepository repository = new SellerRepository();
        List<Seller> sellersList = repository.findByDepartment(2);
        for(Seller seller: sellersList){
            System.out.println(seller);
            System.out.println("-----DEPARTAMENTO-----");
            System.out.println(seller.getDepartment());
        }
    }
}
