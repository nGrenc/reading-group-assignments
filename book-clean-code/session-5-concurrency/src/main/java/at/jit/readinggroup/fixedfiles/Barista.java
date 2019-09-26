package at.jit.readinggroup.fixedfiles;

import java.util.Random;

public final class Barista  {

	private int baristaId;
	private boolean badBladder;

	public Barista(Integer baristaId) {
		this.baristaId = baristaId;
		this.badBladder = new Random().nextInt(2) % 2 == 0;
	}

	Barista(Integer baristaId, boolean badBladder) {
		this.baristaId = baristaId;
		this.badBladder = badBladder;
	}

	public int getBaristaId() {
		return baristaId;
	}

	public boolean hasBadBladder() {
		return badBladder;
	}
}
