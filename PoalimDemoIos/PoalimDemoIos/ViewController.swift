//
//  ViewController.swift
//  PoalimDemoIos
//
//  Created by Dotan Simha on 17/02/2019.
//  Copyright © 2019 The Guild. All rights reserved.
//

import UIKit
import Apollo;

class ViewController: UITableViewController {
    var transactions: [MeQuery.Data.Me.Account.Transaction?]? {
        didSet {
            tableView.reloadData()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.rowHeight = UITableView.automaticDimension
        tableView.estimatedRowHeight = 64
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        loadData()
    }
    
    var watcher: GraphQLQueryWatcher<MeQuery>?
    
    func loadData() {
        watcher = apollo.watch(query: MeQuery()) { (result, error) in
            if let error = error {
                NSLog("Error while fetching query: \(error.localizedDescription)")
                return
            }
            
            self.transactions = result?.data?.me.accounts[0].transactions;
        }
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return transactions?.count ?? 0
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath) as? TableViewCell else {
            fatalError("Could not dequeue TableViewCell")
        }
        
        guard let transaction = transactions?[indexPath.row] else {
            fatalError("Could not find transaction at row \(indexPath.row)")
        }
        
        cell.configure(with: transaction);
        
        return cell;
    }
}

