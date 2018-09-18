package tp3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
    private List<Instrument> instruments;

    public Inventory() {
        instruments = new LinkedList<Instrument>();
    }

    public void display(Instrument g) {
        List<Guitar> matchingGuitars = this.search(g);
        if (!matchingGuitars.isEmpty()) {
            System.out.println("Erin, you might like these guitars:");
            for (Iterator<Guitar> i = matchingGuitars.iterator(); i.hasNext();) {
                Guitar guitar = (Guitar) i.next();
                GuitarSpec gs = (GuitarSpec) guitar.getInstrumentSpec();
                System.out.println("  We have a " + gs.getBuilder() + " " + gs.getModel() + " " + gs.getType() + " guitar:\n     " + gs.getBackWood() + " back and sides,\n     " + gs.getTopWood() + " top.\n  You can have it for only $" + guitar.getPrice() + "!\n  ----");
            }
        }
        else {
            System.out.println("Sorry, Erin, we have nothing for you.");
        }
    }

    public void addInstrument(String serialNumber, double price, InstrumentSpec instrSpec) {
        if (instrSpec instanceof GuitarSpec) {
            instruments.add(new Guitar(serialNumber, price, instrSpec));
        }
        else if (instrSpec instanceof MandolinSpec) {
            instruments.add(new Mandolin(serialNumber, price, instrSpec));
        }
    }

    public Guitar getGuitar(String serialNumber) {
        for (Iterator<Instrument> i = instruments.iterator(); i.hasNext();) {
            Guitar guitar = (Guitar) i.next();
            if (guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }

    public List<Guitar> search(Instrument searchInstr) {
        List<Guitar> matchingGuitars = new LinkedList<Guitar>();
        for (Iterator<Instrument> i = instruments.iterator(); i.hasNext();) {
            Guitar guitar = (Guitar) i.next();
            // Ignore serial number since that's unique
            // Ignore price since that's unique
            if (!searchInstr.getInstrumentSpec().matches(guitar.getInstrumentSpec()))
                continue;
            matchingGuitars.add(guitar);
        }
        return matchingGuitars;
    }
}
