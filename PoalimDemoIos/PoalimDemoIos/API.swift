//  This file was automatically generated and should not be edited.

import Apollo

public final class MeQuery: GraphQLQuery {
  public let operationDefinition =
    "query me {\n  me {\n    __typename\n    accounts {\n      __typename\n      transactions {\n        __typename\n        id\n        date\n        amount\n        ... on MoneyWithdrawTransaction {\n          atmDetails {\n            __typename\n            locationName\n          }\n        }\n        ... on CheckDepositTransaction {\n          checkNumber\n        }\n        ... on TransferTransaction {\n          description\n          source {\n            __typename\n            bank\n            branch\n            owner {\n              __typename\n              id\n              profile {\n                __typename\n                firstName\n                lastName\n              }\n            }\n          }\n        }\n      }\n    }\n  }\n}"

  public init() {
  }

  public struct Data: GraphQLSelectionSet {
    public static let possibleTypes = ["Query"]

    public static let selections: [GraphQLSelection] = [
      GraphQLField("me", type: .nonNull(.object(Me.selections))),
    ]

    public private(set) var resultMap: ResultMap

    public init(unsafeResultMap: ResultMap) {
      self.resultMap = unsafeResultMap
    }

    public init(me: Me) {
      self.init(unsafeResultMap: ["__typename": "Query", "me": me.resultMap])
    }

    public var me: Me {
      get {
        return Me(unsafeResultMap: resultMap["me"]! as! ResultMap)
      }
      set {
        resultMap.updateValue(newValue.resultMap, forKey: "me")
      }
    }

    public struct Me: GraphQLSelectionSet {
      public static let possibleTypes = ["User"]

      public static let selections: [GraphQLSelection] = [
        GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
        GraphQLField("accounts", type: .nonNull(.list(.nonNull(.object(Account.selections))))),
      ]

      public private(set) var resultMap: ResultMap

      public init(unsafeResultMap: ResultMap) {
        self.resultMap = unsafeResultMap
      }

      public init(accounts: [Account]) {
        self.init(unsafeResultMap: ["__typename": "User", "accounts": accounts.map { (value: Account) -> ResultMap in value.resultMap }])
      }

      public var __typename: String {
        get {
          return resultMap["__typename"]! as! String
        }
        set {
          resultMap.updateValue(newValue, forKey: "__typename")
        }
      }

      public var accounts: [Account] {
        get {
          return (resultMap["accounts"] as! [ResultMap]).map { (value: ResultMap) -> Account in Account(unsafeResultMap: value) }
        }
        set {
          resultMap.updateValue(newValue.map { (value: Account) -> ResultMap in value.resultMap }, forKey: "accounts")
        }
      }

      public struct Account: GraphQLSelectionSet {
        public static let possibleTypes = ["CheckingAccount"]

        public static let selections: [GraphQLSelection] = [
          GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
          GraphQLField("transactions", type: .nonNull(.list(.object(Transaction.selections)))),
        ]

        public private(set) var resultMap: ResultMap

        public init(unsafeResultMap: ResultMap) {
          self.resultMap = unsafeResultMap
        }

        public init(transactions: [Transaction?]) {
          self.init(unsafeResultMap: ["__typename": "CheckingAccount", "transactions": transactions.map { (value: Transaction?) -> ResultMap? in value.flatMap { (value: Transaction) -> ResultMap in value.resultMap } }])
        }

        public var __typename: String {
          get {
            return resultMap["__typename"]! as! String
          }
          set {
            resultMap.updateValue(newValue, forKey: "__typename")
          }
        }

        public var transactions: [Transaction?] {
          get {
            return (resultMap["transactions"] as! [ResultMap?]).map { (value: ResultMap?) -> Transaction? in value.flatMap { (value: ResultMap) -> Transaction in Transaction(unsafeResultMap: value) } }
          }
          set {
            resultMap.updateValue(newValue.map { (value: Transaction?) -> ResultMap? in value.flatMap { (value: Transaction) -> ResultMap in value.resultMap } }, forKey: "transactions")
          }
        }

        public struct Transaction: GraphQLSelectionSet {
          public static let possibleTypes = ["MoneyWithdrawTransaction", "TransferTransaction", "CheckDepositTransaction"]

          public static let selections: [GraphQLSelection] = [
            GraphQLTypeCase(
              variants: ["MoneyWithdrawTransaction": AsMoneyWithdrawTransaction.selections, "CheckDepositTransaction": AsCheckDepositTransaction.selections, "TransferTransaction": AsTransferTransaction.selections],
              default: [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
                GraphQLField("date", type: .nonNull(.scalar(String.self))),
                GraphQLField("amount", type: .nonNull(.scalar(Double.self))),
              ]
            )
          ]

          public private(set) var resultMap: ResultMap

          public init(unsafeResultMap: ResultMap) {
            self.resultMap = unsafeResultMap
          }

          public static func makeMoneyWithdrawTransaction(id: GraphQLID, date: String, amount: Double, atmDetails: AsMoneyWithdrawTransaction.AtmDetail) -> Transaction {
            return Transaction(unsafeResultMap: ["__typename": "MoneyWithdrawTransaction", "id": id, "date": date, "amount": amount, "atmDetails": atmDetails.resultMap])
          }

          public static func makeCheckDepositTransaction(id: GraphQLID, date: String, amount: Double, checkNumber: Int) -> Transaction {
            return Transaction(unsafeResultMap: ["__typename": "CheckDepositTransaction", "id": id, "date": date, "amount": amount, "checkNumber": checkNumber])
          }

          public static func makeTransferTransaction(id: GraphQLID, date: String, amount: Double, description: String? = nil, source: AsTransferTransaction.Source) -> Transaction {
            return Transaction(unsafeResultMap: ["__typename": "TransferTransaction", "id": id, "date": date, "amount": amount, "description": description, "source": source.resultMap])
          }

          public var __typename: String {
            get {
              return resultMap["__typename"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "__typename")
            }
          }

          public var id: GraphQLID {
            get {
              return resultMap["id"]! as! GraphQLID
            }
            set {
              resultMap.updateValue(newValue, forKey: "id")
            }
          }

          public var date: String {
            get {
              return resultMap["date"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "date")
            }
          }

          public var amount: Double {
            get {
              return resultMap["amount"]! as! Double
            }
            set {
              resultMap.updateValue(newValue, forKey: "amount")
            }
          }

          public var asMoneyWithdrawTransaction: AsMoneyWithdrawTransaction? {
            get {
              if !AsMoneyWithdrawTransaction.possibleTypes.contains(__typename) { return nil }
              return AsMoneyWithdrawTransaction(unsafeResultMap: resultMap)
            }
            set {
              guard let newValue = newValue else { return }
              resultMap = newValue.resultMap
            }
          }

          public struct AsMoneyWithdrawTransaction: GraphQLSelectionSet {
            public static let possibleTypes = ["MoneyWithdrawTransaction"]

            public static let selections: [GraphQLSelection] = [
              GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
              GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
              GraphQLField("date", type: .nonNull(.scalar(String.self))),
              GraphQLField("amount", type: .nonNull(.scalar(Double.self))),
              GraphQLField("atmDetails", type: .nonNull(.object(AtmDetail.selections))),
            ]

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(id: GraphQLID, date: String, amount: Double, atmDetails: AtmDetail) {
              self.init(unsafeResultMap: ["__typename": "MoneyWithdrawTransaction", "id": id, "date": date, "amount": amount, "atmDetails": atmDetails.resultMap])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            public var id: GraphQLID {
              get {
                return resultMap["id"]! as! GraphQLID
              }
              set {
                resultMap.updateValue(newValue, forKey: "id")
              }
            }

            public var date: String {
              get {
                return resultMap["date"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "date")
              }
            }

            public var amount: Double {
              get {
                return resultMap["amount"]! as! Double
              }
              set {
                resultMap.updateValue(newValue, forKey: "amount")
              }
            }

            public var atmDetails: AtmDetail {
              get {
                return AtmDetail(unsafeResultMap: resultMap["atmDetails"]! as! ResultMap)
              }
              set {
                resultMap.updateValue(newValue.resultMap, forKey: "atmDetails")
              }
            }

            public struct AtmDetail: GraphQLSelectionSet {
              public static let possibleTypes = ["AtmDetails"]

              public static let selections: [GraphQLSelection] = [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("locationName", type: .nonNull(.scalar(String.self))),
              ]

              public private(set) var resultMap: ResultMap

              public init(unsafeResultMap: ResultMap) {
                self.resultMap = unsafeResultMap
              }

              public init(locationName: String) {
                self.init(unsafeResultMap: ["__typename": "AtmDetails", "locationName": locationName])
              }

              public var __typename: String {
                get {
                  return resultMap["__typename"]! as! String
                }
                set {
                  resultMap.updateValue(newValue, forKey: "__typename")
                }
              }

              public var locationName: String {
                get {
                  return resultMap["locationName"]! as! String
                }
                set {
                  resultMap.updateValue(newValue, forKey: "locationName")
                }
              }
            }
          }

          public var asCheckDepositTransaction: AsCheckDepositTransaction? {
            get {
              if !AsCheckDepositTransaction.possibleTypes.contains(__typename) { return nil }
              return AsCheckDepositTransaction(unsafeResultMap: resultMap)
            }
            set {
              guard let newValue = newValue else { return }
              resultMap = newValue.resultMap
            }
          }

          public struct AsCheckDepositTransaction: GraphQLSelectionSet {
            public static let possibleTypes = ["CheckDepositTransaction"]

            public static let selections: [GraphQLSelection] = [
              GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
              GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
              GraphQLField("date", type: .nonNull(.scalar(String.self))),
              GraphQLField("amount", type: .nonNull(.scalar(Double.self))),
              GraphQLField("checkNumber", type: .nonNull(.scalar(Int.self))),
            ]

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(id: GraphQLID, date: String, amount: Double, checkNumber: Int) {
              self.init(unsafeResultMap: ["__typename": "CheckDepositTransaction", "id": id, "date": date, "amount": amount, "checkNumber": checkNumber])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            public var id: GraphQLID {
              get {
                return resultMap["id"]! as! GraphQLID
              }
              set {
                resultMap.updateValue(newValue, forKey: "id")
              }
            }

            public var date: String {
              get {
                return resultMap["date"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "date")
              }
            }

            public var amount: Double {
              get {
                return resultMap["amount"]! as! Double
              }
              set {
                resultMap.updateValue(newValue, forKey: "amount")
              }
            }

            public var checkNumber: Int {
              get {
                return resultMap["checkNumber"]! as! Int
              }
              set {
                resultMap.updateValue(newValue, forKey: "checkNumber")
              }
            }
          }

          public var asTransferTransaction: AsTransferTransaction? {
            get {
              if !AsTransferTransaction.possibleTypes.contains(__typename) { return nil }
              return AsTransferTransaction(unsafeResultMap: resultMap)
            }
            set {
              guard let newValue = newValue else { return }
              resultMap = newValue.resultMap
            }
          }

          public struct AsTransferTransaction: GraphQLSelectionSet {
            public static let possibleTypes = ["TransferTransaction"]

            public static let selections: [GraphQLSelection] = [
              GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
              GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
              GraphQLField("date", type: .nonNull(.scalar(String.self))),
              GraphQLField("amount", type: .nonNull(.scalar(Double.self))),
              GraphQLField("description", type: .scalar(String.self)),
              GraphQLField("source", type: .nonNull(.object(Source.selections))),
            ]

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(id: GraphQLID, date: String, amount: Double, description: String? = nil, source: Source) {
              self.init(unsafeResultMap: ["__typename": "TransferTransaction", "id": id, "date": date, "amount": amount, "description": description, "source": source.resultMap])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            public var id: GraphQLID {
              get {
                return resultMap["id"]! as! GraphQLID
              }
              set {
                resultMap.updateValue(newValue, forKey: "id")
              }
            }

            public var date: String {
              get {
                return resultMap["date"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "date")
              }
            }

            public var amount: Double {
              get {
                return resultMap["amount"]! as! Double
              }
              set {
                resultMap.updateValue(newValue, forKey: "amount")
              }
            }

            public var description: String? {
              get {
                return resultMap["description"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "description")
              }
            }

            public var source: Source {
              get {
                return Source(unsafeResultMap: resultMap["source"]! as! ResultMap)
              }
              set {
                resultMap.updateValue(newValue.resultMap, forKey: "source")
              }
            }

            public struct Source: GraphQLSelectionSet {
              public static let possibleTypes = ["CheckingAccount"]

              public static let selections: [GraphQLSelection] = [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("bank", type: .nonNull(.scalar(Int.self))),
                GraphQLField("branch", type: .nonNull(.scalar(Int.self))),
                GraphQLField("owner", type: .nonNull(.object(Owner.selections))),
              ]

              public private(set) var resultMap: ResultMap

              public init(unsafeResultMap: ResultMap) {
                self.resultMap = unsafeResultMap
              }

              public init(bank: Int, branch: Int, owner: Owner) {
                self.init(unsafeResultMap: ["__typename": "CheckingAccount", "bank": bank, "branch": branch, "owner": owner.resultMap])
              }

              public var __typename: String {
                get {
                  return resultMap["__typename"]! as! String
                }
                set {
                  resultMap.updateValue(newValue, forKey: "__typename")
                }
              }

              public var bank: Int {
                get {
                  return resultMap["bank"]! as! Int
                }
                set {
                  resultMap.updateValue(newValue, forKey: "bank")
                }
              }

              public var branch: Int {
                get {
                  return resultMap["branch"]! as! Int
                }
                set {
                  resultMap.updateValue(newValue, forKey: "branch")
                }
              }

              public var owner: Owner {
                get {
                  return Owner(unsafeResultMap: resultMap["owner"]! as! ResultMap)
                }
                set {
                  resultMap.updateValue(newValue.resultMap, forKey: "owner")
                }
              }

              public struct Owner: GraphQLSelectionSet {
                public static let possibleTypes = ["User"]

                public static let selections: [GraphQLSelection] = [
                  GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                  GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
                  GraphQLField("profile", type: .object(Profile.selections)),
                ]

                public private(set) var resultMap: ResultMap

                public init(unsafeResultMap: ResultMap) {
                  self.resultMap = unsafeResultMap
                }

                public init(id: GraphQLID, profile: Profile? = nil) {
                  self.init(unsafeResultMap: ["__typename": "User", "id": id, "profile": profile.flatMap { (value: Profile) -> ResultMap in value.resultMap }])
                }

                public var __typename: String {
                  get {
                    return resultMap["__typename"]! as! String
                  }
                  set {
                    resultMap.updateValue(newValue, forKey: "__typename")
                  }
                }

                public var id: GraphQLID {
                  get {
                    return resultMap["id"]! as! GraphQLID
                  }
                  set {
                    resultMap.updateValue(newValue, forKey: "id")
                  }
                }

                public var profile: Profile? {
                  get {
                    return (resultMap["profile"] as? ResultMap).flatMap { Profile(unsafeResultMap: $0) }
                  }
                  set {
                    resultMap.updateValue(newValue?.resultMap, forKey: "profile")
                  }
                }

                public struct Profile: GraphQLSelectionSet {
                  public static let possibleTypes = ["Profile"]

                  public static let selections: [GraphQLSelection] = [
                    GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                    GraphQLField("firstName", type: .nonNull(.scalar(String.self))),
                    GraphQLField("lastName", type: .nonNull(.scalar(String.self))),
                  ]

                  public private(set) var resultMap: ResultMap

                  public init(unsafeResultMap: ResultMap) {
                    self.resultMap = unsafeResultMap
                  }

                  public init(firstName: String, lastName: String) {
                    self.init(unsafeResultMap: ["__typename": "Profile", "firstName": firstName, "lastName": lastName])
                  }

                  public var __typename: String {
                    get {
                      return resultMap["__typename"]! as! String
                    }
                    set {
                      resultMap.updateValue(newValue, forKey: "__typename")
                    }
                  }

                  public var firstName: String {
                    get {
                      return resultMap["firstName"]! as! String
                    }
                    set {
                      resultMap.updateValue(newValue, forKey: "firstName")
                    }
                  }

                  public var lastName: String {
                    get {
                      return resultMap["lastName"]! as! String
                    }
                    set {
                      resultMap.updateValue(newValue, forKey: "lastName")
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}