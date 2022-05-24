package org.d3if2132.kalkulatorbmr.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if2132.kalkulatorbmr.db.BmrDao

class HistoriViewModel(db: BmrDao) : ViewModel() {
    val data = db.getLastBmr()
}
