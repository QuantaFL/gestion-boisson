# Lot API Endpoints

This document describes the available endpoints for the Lot API.

## Health Check

*   **Method:** `GET`
*   **Path:** `/lots/health`
*   **Description:** Checks if the lot service is running.
*   **Example Response:**
    ```
    Lot service is running
    ```

## Get All Available Lots

*   **Method:** `GET`
*   **Path:** `/lots`
*   **Description:** Retrieves all available lots.
*   **Example Response:**
    ```json
    [
        {
            "id": 1,
            "numeroLot": "LOT-20250706-01",
            "quantiteInitiale": 100,
            "quantiteActuelle": 100,
            "dateEntree": "2025-07-06T10:00:00",
            "datePeremption": "2025-12-31T00:00:00",
            "vendable": true,
            "boisson": {
                "id": 5,
                "nom": "Coca-Cola",
                "prix": 1.5,
                "quantite": 500,
                "categorie": "Soda"
            }
        }
    ]
    ```

## Add a New Lot

*   **Method:** `POST`
*   **Path:** `/lots/`
*   **Description:** Adds a new lot.
*   **Example Request Body:**
    ```json
    {
        "numeroLot": "LOT-20250706-02",
        "quantiteInitiale": 200,
        "datePeremption": "2026-06-30T00:00:00",
        "boisson": {
            "id": 10
        }
    }
    ```
*   **Example Response:**
    ```json
    {
        "id": 2,
        "numeroLot": "LOT-20250706-02",
        "quantiteInitiale": 200,
        "quantiteActuelle": 200,
        "dateEntree": "2025-07-06T11:00:00",
        "datePeremption": "2026-06-30T00:00:00",
        "vendable": true,
        "boisson": {
            "id": 10,
            "nom": "Fanta",
            "prix": 1.2,
            "quantite": 330,
            "categorie": "Soda"
        }
    }
    
    ```

## Update an Existing Lot

*   **Method:** `PUT`
*   **Path:** `/lots/`
*   **Description:** Updates an existing lot.
*   **Example Request Body:**
    ```json
    {
        "id": 1,
        "numeroLot": "LOT-20250706-01",
        "quantiteInitiale": 100,
        "quantiteActuelle": 80,
        "datePeremption": "2025-12-31T00:00:00",
        "vendable": true,
        "boisson": {
            "id": 5
        }
    }
    ```

## Check if a Lot is Available

*   **Method:** `GET`
*   **Path:** `/lots/{lotId}/is-available`
*   **Description:** Checks if a lot is available.
*   **Example Response:**
    ```json
    true
    ```

## Check if a Lot is Expired

*   **Method:** `GET`
*   **Path:** `/lots/{lotId}/is-expired`
*   **Description:** Checks if a lot is expired.
*   **Example Response:**
    ```json
    false
    ```

## Get Lots by Expiration Order

*   **Method:** `GET`
*   **Path:** `/lots/boisson/{boissonId}/ordre-peremption`
*   **Description:** Retrieves all lots for a given beverage, ordered by expiration date.
*   **Example Response:**
    ```json
    [
        {
            "id": 2,
            "numeroLot": "LOT-20250815-01",
            "quantiteInitiale": 150,
            "quantiteActuelle": 150,
            "dateEntree": "2025-07-06T11:00:00",
            "datePeremption": "2025-08-15T00:00:00",
            "vendable": true,
            "boisson": {
                "id": 5,
                "nom": "Coca-Cola",
                "prix": 1.5,
                "quantite": 500,
                "categorie": "Soda"
            }
        },
        {
            "id": 1,
            "numeroLot": "LOT-20250706-01",
            "quantiteInitiale": 100,
            "quantiteActuelle": 80,
            "dateEntree": "2025-07-06T10:00:00",
            "datePeremption": "2025-12-31T00:00:00",
            "vendable": true,
            "boisson": {
                "id": 5,
                "nom": "Coca-Cola",
                "prix": 1.5,
                "quantite": 500,
                "categorie": "Soda"
            }
        }
    ]
    ```

## Remove Quantity from a Lot

*   **Method:** `POST`
*   **Path:** `/lots/{lotId}/remove-quantity?quantity={quantity}`
*   **Description:** Removes a given quantity from a lot.
*   **Example:**
    `POST /lots/1/remove-quantity?quantity=20`
