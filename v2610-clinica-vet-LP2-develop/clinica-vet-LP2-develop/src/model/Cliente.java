package model;

import exception.AnimalInexistenteException;
import exception.DadosObrigatoriosException;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    private ArrayList<Animal> listaDeAnimais;

    public Cliente(String nome, String cpf, String endereco, String telefone, ArrayList<Animal> listaDeAnimais) throws DadosObrigatoriosException {
        super(nome, cpf, endereco, telefone);
        if (listaDeAnimais == null) {
            throw new DadosObrigatoriosException("Um cliente deverá ter pelo menos um animal");
        }
        this.listaDeAnimais = listaDeAnimais;
    }

    public void agendarConsulta() {
        // Implementar
    }

    public void atualizarDados(String nome, String endereco, String telefone) {
        if (listaDeAnimais == null) {
            throw new DadosObrigatoriosException("Um cliente deverá ter pelo menos um animal");
        } 
        super.atualizarDados(nome, endereco, telefone); //sobrecarga do pai
    }

    @Override
    public void exibirDados() {
        // Dados do cliente
        super.exibirDados();
    }

    public ArrayList<Animal> getAnimais() {
        return this.listaDeAnimais;
    }

    // CRUD de animais do cliente
    public void adicionarAnimal(Animal animal) {
        this.listaDeAnimais.add(animal);
    }

    public void removerAnimal(int id) {
        this.listaDeAnimais.removeIf(animal -> animal.getId() == id);
    }

    public void atualizarAnimal(Animal animal) {
        Animal animalParaAtualizar = this.listaDeAnimais.stream().filter(animalAntigo -> animalAntigo.getId() == animal.getId()).findFirst().orElse(null);

        if (animalParaAtualizar != null) {
            animalParaAtualizar.atualizarDados(animal.getNome(), animal.getEspecie(), animal.getRaca(), animal.getDataNascimento());
        } else {
            throw new AnimalInexistenteException("Animal de ID: " + animal.getId() + " , não foi encontrado");
        }
    }

    public void mostrarListaDeAnimais() {
        if (this.listaDeAnimais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            for (Animal animal : this.listaDeAnimais) {
                System.out.println("[ ID: " + animal.getId() + " | Nome: " + animal.getNome() + " | Espécie: " + animal.getEspecie() + " | Raça: " + animal.getRaca() + " ]");
            }
        }
    }

    public void registrarPesoAnimal(int id, float peso) {
        Animal animalParaAtualizar = this.listaDeAnimais.stream().filter(animal -> animal.getId() == id).findFirst().orElse(null);

        if (animalParaAtualizar != null) {
            animalParaAtualizar.registrarPeso(peso);
        } else {
            throw new AnimalInexistenteException("Animal de ID: " + id + " , não foi encontrado");
        }
    }

    public void registrarObservacaoAnimal(int id, String observacao) {
        Animal animalParaAtualizar = this.listaDeAnimais.stream().filter(animal -> animal.getId() == id).findFirst().orElse(null);

        if(animalParaAtualizar != null) {
            animalParaAtualizar.registrarObservacao(observacao);
        } else {
            throw new AnimalInexistenteException("Animal de ID: " + id + " , não foi encontrado"); 
        }
    }
    
}
