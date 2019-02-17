import UIKit
import Apollo

class TableViewCell: UITableViewCell {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var bylineLabel: UILabel!
    @IBOutlet weak var votesLabel: UILabel!
    
    func configure(with transaction: MeQuery.Data.Me.Account.Transaction) {
        titleLabel?.text = transaction.__typename + " " + String(transaction.amount);
        bylineLabel?.text = transaction.date;
    }

}
